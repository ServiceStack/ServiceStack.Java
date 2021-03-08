package net.servicestack.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public final class Inspect {

    public static final void vars(Map args) {
        String inspectVarsPath = System.getenv("INSPECT_VARS");
        if (inspectVarsPath == null)
            return;

        FileWriter writer = null;

        try {
            if (System.getProperty("os.name").toLowerCase().contains("win")) {
                inspectVarsPath = inspectVarsPath.replace("/", "\\");
            } else {
                inspectVarsPath = inspectVarsPath.replace("\\", "/");
            }

            Path dirPath = Paths.get(inspectVarsPath).getParent();
            Files.createDirectories(dirPath);

            writer = new FileWriter(inspectVarsPath);

            new Gson().toJson(args, writer);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static final String dump(Object obj) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(obj);
        return json.replace("\"", "");
    }

    public static final void printDump(Object obj) {
        System.out.println(dump(obj));
    }

    public static final String dumpTable(Iterable objs, Iterable headers) {
        List rows = Utils.toList(objs);
        List<Map<String, Object>> mapRows = toListMap(rows);

        List<String> keys = headers != null ? Utils.toList(headers) : allKeys(mapRows);
        HashMap<String, Integer> colSizes = new HashMap<>();

        for (String k : keys) {
            int max = k.length();
            for (Map<String, Object> row : mapRows) {
                if (row.containsKey(k)) {
                    Object col = row.get(k);
                    int valSize = col.toString().length();
                    if (valSize > max) {
                        max = valSize;
                    }
                }
            }
            colSizes.put(k, max);
        }

        // sum + ' padding ' + |
        int rowWidth = Utils.sum(colSizes.values()) +
                (colSizes.size() * 2) +
                (colSizes.size() + 1);

        StringBuilder sb = new StringBuilder();
        Utils.appendLine(sb.append("+").append(Utils.repeat("-", rowWidth - 2)).append("+"));
        sb.append("|");
        for (String k : keys) {
            sb.append(alignCenter(k, colSizes.get(k))).append("|");
        }
        Utils.appendLine(sb);

        Utils.appendLine(sb.append("|").append(Utils.repeat("-", rowWidth - 2)).append("|"));

        for (Map<String, Object> row : mapRows) {
            sb.append("|");
            for (String k : keys) {
                sb.append(alignAuto(row.get(k), colSizes.get(k))).append("|");
            }
            Utils.appendLine(sb);
        }
        Utils.appendLine(sb.append("+").append(Utils.repeat("-", rowWidth - 2)).append("+"));

        return sb.toString();
    }

    public static final void printDumpTable(Iterable objs) {
        System.out.println(dumpTable(objs, null));
    }

    public static final void printDumpTable(Iterable objs, Iterable headers) {
        System.out.println(dumpTable(objs, headers));
    }

    public static final List<String> allKeys(List<Map<String, Object>> rows) {
        ArrayList<String> to = new ArrayList<>();
        for (Map<String, Object> row : rows) {
            for (String key : row.keySet()) {
                if (!to.contains(key)) {
                    to.add(key);
                }
            }
        }
        return to;
    }

    public static final boolean isNumber(Object obj) {
        return obj instanceof Long || obj instanceof Integer || obj instanceof Short ||
                obj instanceof Byte || obj instanceof Double || obj instanceof Float;
    }

    private static final String fmtNumber(Object d) {
        if (d instanceof Double) {
            Double dbl = ((Number) d).doubleValue();
            return dbl == Math.floor(dbl) ? String.valueOf(((Number) dbl).longValue()) : String.valueOf(dbl);
        } else if (d instanceof Float) {
            Float f = ((Number) d).floatValue();
            return f == Math.floor(f) ? String.valueOf(((Number) f).longValue()) : String.valueOf(f);
        }
        return d.toString();
    }

    public static final String alignLeft(String str, Integer len) {
        return alignLeft(str, len, " ");
    }

    public static final String alignLeft(String str, int len, String pad) {
        if (len < 0) return "";
        int aLen = len + 1 - str.length();
        return aLen <= 0 ? str : pad + str + Utils.repeat(pad, aLen);
    }

    public static final String alignCenter(String str, int len) {
        return alignCenter(str, len, " ");
    }

    public static final String alignCenter(String str, int len, String pad) {
        if (len < 0) return "";
        int nLen = str.length();
        int half = (int) Math.floor(len / 2.0 - nLen / 2.0);
        int odds = Math.abs((nLen % 2) - (len % 2));
        return (Utils.repeat(pad, half + 1)) + str + (Utils.repeat(pad, half + 1 + odds));
    }

    public static final String alignRight(String str, int len) {
        return alignRight(str, len, " ");
    }

    public static final String alignRight(String str, int len, String pad) {
        if (len < 0) return "";
        int aLen = len + 1 - str.length();
        return aLen <= 0 ? str : Utils.repeat(pad, aLen) + str + pad;
    }

    public static final String alignAuto(Object obj, int len) {
        return alignAuto(obj, len, " ");
    }

    public static final String alignAuto(Object obj, int len, String pad) {
        String str = obj == null ? "" : String.valueOf(obj);
        if (str.length() <= len) {
            return isNumber(obj)
                    ? alignRight(fmtNumber(obj), len, pad)
                    : alignLeft(str, len, pad);
        } else {
            return str;
        }
    }

    public static final List<Map<String, Object>> toListMap(List objs) {
        Gson gson = new Gson();
        String json = gson.toJson(objs);
        return gson.fromJson(json, new TypeToken<List<Map<String, Object>>>() {
        }.getType());
    }

    public static final Map<String, Object> toMap(Object obj) {
        Gson gson = new Gson();
        String json = gson.toJson(obj);
        return gson.fromJson(json, new TypeToken<Map<String, Object>>() {
        }.getType());
    }

    public static final String readUrlAsText(URL url) {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }
}
