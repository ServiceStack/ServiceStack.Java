package servicestack.net.techstackskotlin

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.support.v4.app.FragmentActivity
import net.servicestack.android.AndroidServiceClient
import net.servicestack.android.AndroidUtils
import net.servicestack.client.AsyncSuccess
import net.servicestack.client.Utils
import java.util.*

class App {

    internal var client: AndroidServiceClient
    var appData: AppData

    init {
        client = AndroidServiceClient("https://www.techstacks.io")
        appData = AppData(client)
    }

    class AppData(internal var client: AndroidServiceClient) {

        internal var listeners = ArrayList<AppDataListener>()

        fun addListener(callback: AppDataListener): AppData {
            if (!listeners.contains(callback)) {
                listeners.add(callback)
            }
            return this
        }

        fun onUpdate(dataType: DataType) {
            for (listener in listeners) {
                listener.onUpdate(this, dataType)
            }
        }

        fun loadAppOverview(): AppData {
            if (appOverviewResponse != null) {
                onUpdate(DataType.AppOverview)
            }
            client.getAsync(AppOverview(), AsyncSuccess<AppOverviewResponse> {
                appOverviewResponse = it
                onUpdate(DataType.AppOverview)
            })
            return this
        }

        var appOverviewResponse: AppOverviewResponse? = null
            internal set

        internal var lastTechStacksQuery: String? = null

        fun searchTechStacks(query: String): AppData {
            if (searchTechStacksResponse != null && Utils.equals(query, lastTechStacksQuery)) {
                onUpdate(DataType.SearchTechStacks)
            }

            lastTechStacksQuery = query
            client.getAsync(FindTechStacks(),
                    hashMapOf(Pair("NameContains", query), Pair("DescriptionContains", query)),
                    AsyncSuccess<QueryResponse<TechnologyStack>> {
                        if (Utils.equals(query, lastTechStacksQuery)) {
                            searchTechStacksResponse = it
                            onUpdate(DataType.SearchTechStacks)
                        }
                    })

            return this
        }

        var searchTechStacksResponse: QueryResponse<TechnologyStack>? = null
            internal set

        internal var lastTechnologiesQuery: String? = null

        fun searchTechnologies(query: String): AppData {
            if (searchTechnologiesResponse != null && Utils.equals(query, lastTechnologiesQuery)) {
                onUpdate(DataType.SearchTechnologies)
            }

            lastTechnologiesQuery = query
            client.getAsync(FindTechnologies(),
                    hashMapOf(Pair("NameContains", query), Pair("DescriptionContains", query)),
                    AsyncSuccess<QueryResponse<Technology>> {
                        if (Utils.equals(query, lastTechnologiesQuery)) {
                            searchTechnologiesResponse = it
                            onUpdate(DataType.SearchTechnologies)
                        }
                    })

            return this
        }

        var searchTechnologiesResponse: QueryResponse<Technology>? = null
            internal set

        var technology: GetTechnologyResponse? = null
            internal set

        fun loadTechnology(slug: String) {
            if (technology != null) {
                onUpdate(DataType.Technology)
            }

            val request = GetTechnology()
            request.slug = slug
            client.getAsync(request, AsyncSuccess<GetTechnologyResponse> {
                technology = it
                onUpdate(DataType.Technology)
            })
        }

        var techStack: GetTechnologyStackResponse? = null
            internal set

        fun loadTechStack(slug: String) {
            if (techStack != null) {
                onUpdate(DataType.TechStack)
            }

            val request = GetTechnologyStack()
            request.slug = slug
            client.getAsync(request, AsyncSuccess<GetTechnologyStackResponse> {
                techStack = it
                onUpdate(DataType.TechStack)
            })
        }

        internal var imgCache = HashMap<String, Bitmap>()
        fun loadImage(imgUrl: String, callback: ImageResult) {
            val img = imgCache[imgUrl]
            if (img != null) {
                callback.success(img)
                return
            }

            client.getAsync(imgUrl, {
                val img = AndroidUtils.readBitmap(it)
                imgCache.put(imgUrl, img)
                callback.success(img)
            })
        }
    }

    interface AppDataListener {
        fun onUpdate(data: AppData, dataType: DataType)
    }

    interface ImageResult {
        fun success(img: Bitmap)
    }

    enum class DataType {
        AppOverview,
        SearchTechStacks,
        SearchTechnologies,
        Technology,
        TechStack
    }

    companion object {

        var Instance = App()

        fun get(): App {
            return Instance
        }

        val data: AppData
            get() = get().appData

        fun openTechStack(activity: Activity?, slug: String?) {
            if (slug == null) return
            val openTechStack = Intent(activity, TechStackActivity::class.java)
            openTechStack.putExtra("slug", slug)
            activity!!.startActivity(openTechStack)
        }

        fun openTechnology(activity: Activity?, slug: String?) {
            if (slug == null) return
            val openTechnology = Intent(activity, TechnologyActivity::class.java)
            openTechnology.putExtra("slug", slug)
            activity!!.startActivity(openTechnology)
        }

        fun openUrl(activity: Activity, url: String?) {
            if (url == null) return
            activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        }
    }
}
