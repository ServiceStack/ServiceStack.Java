/* Options:
Date: 2024-10-23 04:54:03
Version: 8.41
Tip: To override a DTO option, remove "//" prefix before updating
BaseUrl: https://openai.servicestack.net

Package: net.servicestack.openai
//GlobalNamespace: dtos
//AddPropertyAccessors: True
//SettersReturnThis: True
//AddServiceStackTypes: True
//AddResponseStatus: False
//AddDescriptionAsComments: True
//AddImplicitVersion: 
//IncludeTypes: 
//ExcludeTypes: 
//TreatTypesAsStrings: 
DefaultImports: java.math.*,java.util.*,net.servicestack.client.*,com.google.gson.annotations.*,com.google.gson.reflect.*,java.io.InputStream
*/

package net.servicestack.openai;

import java.math.*;
import java.util.*;
import net.servicestack.client.*;
import com.google.gson.annotations.*;
import com.google.gson.reflect.*;
import java.io.InputStream;

public class dtos
{

    public static class AdminData implements IReturn<AdminDataResponse>, IGet
    {
        
        private static Object responseType = AdminDataResponse.class;
        public Object getResponseType() { return responseType; }
    }

    /**
    * Convert an audio file to a different format
    */
    public static class ConvertAudio implements IReturn<MediaTransformResponse>, IMediaTransform
    {
        /**
        * The desired output format for the converted audio
        */
        @ApiMember(Description="The desired output format for the converted audio")
        @Required()
        public AudioFormat outputFormat = null;

        @Required()
        public InputStream audio = null;

        /**
        * Optional client-provided identifier for the request
        */
        @ApiMember(Description="Optional client-provided identifier for the request")
        public String refId = null;

        /**
        * Tag to identify the request
        */
        @ApiMember(Description="Tag to identify the request")
        public String tag = null;
        
        public AudioFormat getOutputFormat() { return outputFormat; }
        public ConvertAudio setOutputFormat(AudioFormat value) { this.outputFormat = value; return this; }
        public InputStream getAudio() { return audio; }
        public ConvertAudio setAudio(InputStream value) { this.audio = value; return this; }
        public String getRefId() { return refId; }
        public ConvertAudio setRefId(String value) { this.refId = value; return this; }
        public String getTag() { return tag; }
        public ConvertAudio setTag(String value) { this.tag = value; return this; }
        private static Object responseType = MediaTransformResponse.class;
        public Object getResponseType() { return responseType; }
    }

    /**
    * Convert an audio file to a different format
    */
    public static class QueueConvertAudio implements IReturn<QueueMediaTransformResponse>, IQueueMediaTransform
    {
        /**
        * The desired output format for the converted audio
        */
        @ApiMember(Description="The desired output format for the converted audio")
        @Required()
        public AudioFormat outputFormat = null;

        @Required()
        public InputStream audio = null;

        /**
        * Optional client-provided identifier for the request
        */
        @ApiMember(Description="Optional client-provided identifier for the request")
        public String refId = null;

        /**
        * Optional queue or topic to reply to
        */
        @ApiMember(Description="Optional queue or topic to reply to")
        public String replyTo = null;

        /**
        * Tag to identify the request
        */
        @ApiMember(Description="Tag to identify the request")
        public String tag = null;
        
        public AudioFormat getOutputFormat() { return outputFormat; }
        public QueueConvertAudio setOutputFormat(AudioFormat value) { this.outputFormat = value; return this; }
        public InputStream getAudio() { return audio; }
        public QueueConvertAudio setAudio(InputStream value) { this.audio = value; return this; }
        public String getRefId() { return refId; }
        public QueueConvertAudio setRefId(String value) { this.refId = value; return this; }
        public String getReplyTo() { return replyTo; }
        public QueueConvertAudio setReplyTo(String value) { this.replyTo = value; return this; }
        public String getTag() { return tag; }
        public QueueConvertAudio setTag(String value) { this.tag = value; return this; }
        private static Object responseType = QueueMediaTransformResponse.class;
        public Object getResponseType() { return responseType; }
    }

    public static class GetSummaryStats implements IReturn<GetSummaryStatsResponse>, IGet
    {
        public Date from = null;
        public Date to = null;
        
        public Date getFrom() { return from; }
        public GetSummaryStats setFrom(Date value) { this.from = value; return this; }
        public Date getTo() { return to; }
        public GetSummaryStats setTo(Date value) { this.to = value; return this; }
        private static Object responseType = GetSummaryStatsResponse.class;
        public Object getResponseType() { return responseType; }
    }

    public static class PopulateChatSummary implements IReturn<StringsResponse>, IGet
    {
        
        private static Object responseType = StringsResponse.class;
        public Object getResponseType() { return responseType; }
    }

    public static class GetComfyModels implements IReturn<GetComfyModelsResponse>
    {
        public String apiBaseUrl = null;
        public String apiKey = null;
        
        public String getApiBaseUrl() { return apiBaseUrl; }
        public GetComfyModels setApiBaseUrl(String value) { this.apiBaseUrl = value; return this; }
        public String getApiKey() { return apiKey; }
        public GetComfyModels setApiKey(String value) { this.apiKey = value; return this; }
        private static Object responseType = GetComfyModelsResponse.class;
        public Object getResponseType() { return responseType; }
    }

    public static class GetComfyModelMappings implements IReturn<GetComfyModelMappingsResponse>
    {
        
        private static Object responseType = GetComfyModelMappingsResponse.class;
        public Object getResponseType() { return responseType; }
    }

    /**
    * Get job status
    */
    @Api(Description="Get job status")
    public static class GetJobStatus implements IReturn<GetJobStatusResponse>, IGet
    {
        /**
        * Unique identifier of the background job
        */
        @ApiMember(Description="Unique identifier of the background job")
        public Long jobId = null;

        /**
        * Client-provided identifier for the request
        */
        @ApiMember(Description="Client-provided identifier for the request")
        public String refId = null;
        
        public Long getJobId() { return jobId; }
        public GetJobStatus setJobId(Long value) { this.jobId = value; return this; }
        public String getRefId() { return refId; }
        public GetJobStatus setRefId(String value) { this.refId = value; return this; }
        private static Object responseType = GetJobStatusResponse.class;
        public Object getResponseType() { return responseType; }
    }

    /**
    * Active Media Worker Models available in AI Server
    */
    public static class ActiveMediaModels implements IReturn<StringsResponse>, IGet
    {
        
        private static Object responseType = StringsResponse.class;
        public Object getResponseType() { return responseType; }
    }

    /**
    * Generate image from text description
    */
    @Api(Description="Generate image from text description")
    public static class TextToImage implements IReturn<GenerationResponse>, IGeneration
    {
        /**
        * The main prompt describing the desired image
        */
        @ApiMember(Description="The main prompt describing the desired image")
        @Validate(Validator="NotEmpty")
        public String positivePrompt = null;

        /**
        * Optional prompt specifying what should not be in the image
        */
        @ApiMember(Description="Optional prompt specifying what should not be in the image")
        public String negativePrompt = null;

        /**
        * Desired width of the generated image
        */
        @ApiMember(Description="Desired width of the generated image")
        public Integer width = null;

        /**
        * Desired height of the generated image
        */
        @ApiMember(Description="Desired height of the generated image")
        public Integer height = null;

        /**
        * Number of images to generate in a single batch
        */
        @ApiMember(Description="Number of images to generate in a single batch")
        public Integer batchSize = null;

        /**
        * The AI model to use for image generation
        */
        @ApiMember(Description="The AI model to use for image generation")
        public String model = null;

        /**
        * Optional seed for reproducible results
        */
        @ApiMember(Description="Optional seed for reproducible results")
        public Integer seed = null;

        /**
        * Optional client-provided identifier for the request
        */
        @ApiMember(Description="Optional client-provided identifier for the request")
        public String refId = null;

        /**
        * Tag to identify the request
        */
        @ApiMember(Description="Tag to identify the request")
        public String tag = null;
        
        public String getPositivePrompt() { return positivePrompt; }
        public TextToImage setPositivePrompt(String value) { this.positivePrompt = value; return this; }
        public String getNegativePrompt() { return negativePrompt; }
        public TextToImage setNegativePrompt(String value) { this.negativePrompt = value; return this; }
        public Integer getWidth() { return width; }
        public TextToImage setWidth(Integer value) { this.width = value; return this; }
        public Integer getHeight() { return height; }
        public TextToImage setHeight(Integer value) { this.height = value; return this; }
        public Integer getBatchSize() { return batchSize; }
        public TextToImage setBatchSize(Integer value) { this.batchSize = value; return this; }
        public String getModel() { return model; }
        public TextToImage setModel(String value) { this.model = value; return this; }
        public Integer getSeed() { return seed; }
        public TextToImage setSeed(Integer value) { this.seed = value; return this; }
        public String getRefId() { return refId; }
        public TextToImage setRefId(String value) { this.refId = value; return this; }
        public String getTag() { return tag; }
        public TextToImage setTag(String value) { this.tag = value; return this; }
        private static Object responseType = GenerationResponse.class;
        public Object getResponseType() { return responseType; }
    }

    /**
    * Generate image from another image
    */
    @Api(Description="Generate image from another image")
    public static class ImageToImage implements IReturn<GenerationResponse>, IGeneration
    {
        /**
        * The image to use as input
        */
        @ApiMember(Description="The image to use as input")
        @Required()
        public InputStream image = null;

        /**
        * Prompt describing the desired output
        */
        @ApiMember(Description="Prompt describing the desired output")
        @Validate(Validator="NotEmpty")
        public String positivePrompt = null;

        /**
        * Negative prompt describing what should not be in the image
        */
        @ApiMember(Description="Negative prompt describing what should not be in the image")
        public String negativePrompt = null;

        /**
        * The AI model to use for image generation
        */
        @ApiMember(Description="The AI model to use for image generation")
        public String model = null;

        /**
        * Optional specific amount of denoise to apply
        */
        @ApiMember(Description="Optional specific amount of denoise to apply")
        public Float denoise = null;

        /**
        * Number of images to generate in a single batch
        */
        @ApiMember(Description="Number of images to generate in a single batch")
        public Integer batchSize = null;

        /**
        * Optional seed for reproducible results in image generation
        */
        @ApiMember(Description="Optional seed for reproducible results in image generation")
        public Integer seed = null;

        /**
        * Optional client-provided identifier for the request
        */
        @ApiMember(Description="Optional client-provided identifier for the request")
        public String refId = null;

        /**
        * Tag to identify the request
        */
        @ApiMember(Description="Tag to identify the request")
        public String tag = null;
        
        public InputStream getImage() { return image; }
        public ImageToImage setImage(InputStream value) { this.image = value; return this; }
        public String getPositivePrompt() { return positivePrompt; }
        public ImageToImage setPositivePrompt(String value) { this.positivePrompt = value; return this; }
        public String getNegativePrompt() { return negativePrompt; }
        public ImageToImage setNegativePrompt(String value) { this.negativePrompt = value; return this; }
        public String getModel() { return model; }
        public ImageToImage setModel(String value) { this.model = value; return this; }
        public Float getDenoise() { return denoise; }
        public ImageToImage setDenoise(Float value) { this.denoise = value; return this; }
        public Integer getBatchSize() { return batchSize; }
        public ImageToImage setBatchSize(Integer value) { this.batchSize = value; return this; }
        public Integer getSeed() { return seed; }
        public ImageToImage setSeed(Integer value) { this.seed = value; return this; }
        public String getRefId() { return refId; }
        public ImageToImage setRefId(String value) { this.refId = value; return this; }
        public String getTag() { return tag; }
        public ImageToImage setTag(String value) { this.tag = value; return this; }
        private static Object responseType = GenerationResponse.class;
        public Object getResponseType() { return responseType; }
    }

    /**
    * Upscale an image
    */
    @Api(Description="Upscale an image")
    public static class ImageUpscale implements IReturn<GenerationResponse>, IGeneration
    {
        /**
        * The image to upscale
        */
        @ApiMember(Description="The image to upscale")
        @Required()
        public InputStream image = null;

        /**
        * Optional seed for reproducible results in image generation
        */
        @ApiMember(Description="Optional seed for reproducible results in image generation")
        public Integer seed = null;

        /**
        * Optional client-provided identifier for the request
        */
        @ApiMember(Description="Optional client-provided identifier for the request")
        public String refId = null;

        /**
        * Tag to identify the request
        */
        @ApiMember(Description="Tag to identify the request")
        public String tag = null;
        
        public InputStream getImage() { return image; }
        public ImageUpscale setImage(InputStream value) { this.image = value; return this; }
        public Integer getSeed() { return seed; }
        public ImageUpscale setSeed(Integer value) { this.seed = value; return this; }
        public String getRefId() { return refId; }
        public ImageUpscale setRefId(String value) { this.refId = value; return this; }
        public String getTag() { return tag; }
        public ImageUpscale setTag(String value) { this.tag = value; return this; }
        private static Object responseType = GenerationResponse.class;
        public Object getResponseType() { return responseType; }
    }

    /**
    * Generate image with masked area
    */
    @Api(Description="Generate image with masked area")
    public static class ImageWithMask implements IReturn<GenerationResponse>, IGeneration
    {
        /**
        * Prompt describing the desired output in the masked area
        */
        @ApiMember(Description="Prompt describing the desired output in the masked area")
        @Validate(Validator="NotEmpty")
        public String positivePrompt = null;

        /**
        * Negative prompt describing what should not be in the masked area
        */
        @ApiMember(Description="Negative prompt describing what should not be in the masked area")
        public String negativePrompt = null;

        /**
        * The image to use as input
        */
        @ApiMember(Description="The image to use as input")
        @Required()
        public InputStream image = null;

        /**
        * The mask to use as input
        */
        @ApiMember(Description="The mask to use as input")
        @Required()
        public InputStream mask = null;

        /**
        * Optional specific amount of denoise to apply
        */
        @ApiMember(Description="Optional specific amount of denoise to apply")
        public Float denoise = null;

        /**
        * Optional seed for reproducible results in image generation
        */
        @ApiMember(Description="Optional seed for reproducible results in image generation")
        public Integer seed = null;

        /**
        * Optional client-provided identifier for the request
        */
        @ApiMember(Description="Optional client-provided identifier for the request")
        public String refId = null;

        /**
        * Tag to identify the request
        */
        @ApiMember(Description="Tag to identify the request")
        public String tag = null;
        
        public String getPositivePrompt() { return positivePrompt; }
        public ImageWithMask setPositivePrompt(String value) { this.positivePrompt = value; return this; }
        public String getNegativePrompt() { return negativePrompt; }
        public ImageWithMask setNegativePrompt(String value) { this.negativePrompt = value; return this; }
        public InputStream getImage() { return image; }
        public ImageWithMask setImage(InputStream value) { this.image = value; return this; }
        public InputStream getMask() { return mask; }
        public ImageWithMask setMask(InputStream value) { this.mask = value; return this; }
        public Float getDenoise() { return denoise; }
        public ImageWithMask setDenoise(Float value) { this.denoise = value; return this; }
        public Integer getSeed() { return seed; }
        public ImageWithMask setSeed(Integer value) { this.seed = value; return this; }
        public String getRefId() { return refId; }
        public ImageWithMask setRefId(String value) { this.refId = value; return this; }
        public String getTag() { return tag; }
        public ImageWithMask setTag(String value) { this.tag = value; return this; }
        private static Object responseType = GenerationResponse.class;
        public Object getResponseType() { return responseType; }
    }

    /**
    * Convert image to text
    */
    @Api(Description="Convert image to text")
    public static class ImageToText implements IReturn<GenerationResponse>, IGeneration
    {
        /**
        * The image to convert to text
        */
        @ApiMember(Description="The image to convert to text")
        @Required()
        public InputStream image = null;

        /**
        * Optional client-provided identifier for the request
        */
        @ApiMember(Description="Optional client-provided identifier for the request")
        public String refId = null;

        /**
        * Tag to identify the request
        */
        @ApiMember(Description="Tag to identify the request")
        public String tag = null;
        
        public InputStream getImage() { return image; }
        public ImageToText setImage(InputStream value) { this.image = value; return this; }
        public String getRefId() { return refId; }
        public ImageToText setRefId(String value) { this.refId = value; return this; }
        public String getTag() { return tag; }
        public ImageToText setTag(String value) { this.tag = value; return this; }
        private static Object responseType = GenerationResponse.class;
        public Object getResponseType() { return responseType; }
    }

    /**
    * Generate image from text description
    */
    @Api(Description="Generate image from text description")
    public static class QueueTextToImage implements IReturn<QueueGenerationResponse>, IQueueGeneration
    {
        /**
        * The main prompt describing the desired image
        */
        @ApiMember(Description="The main prompt describing the desired image")
        @Validate(Validator="NotEmpty")
        public String positivePrompt = null;

        /**
        * Optional prompt specifying what should not be in the image
        */
        @ApiMember(Description="Optional prompt specifying what should not be in the image")
        public String negativePrompt = null;

        /**
        * Desired width of the generated image
        */
        @ApiMember(Description="Desired width of the generated image")
        public Integer width = null;

        /**
        * Desired height of the generated image
        */
        @ApiMember(Description="Desired height of the generated image")
        public Integer height = null;

        /**
        * Number of images to generate in a single batch
        */
        @ApiMember(Description="Number of images to generate in a single batch")
        public Integer batchSize = null;

        /**
        * The AI model to use for image generation
        */
        @ApiMember(Description="The AI model to use for image generation")
        public String model = null;

        /**
        * Optional seed for reproducible results
        */
        @ApiMember(Description="Optional seed for reproducible results")
        public Integer seed = null;

        /**
        * Optional client-provided identifier for the request
        */
        @ApiMember(Description="Optional client-provided identifier for the request")
        public String refId = null;

        /**
        * Optional queue or topic to reply to
        */
        @ApiMember(Description="Optional queue or topic to reply to")
        public String replyTo = null;

        /**
        * Tag to identify the request
        */
        @ApiMember(Description="Tag to identify the request")
        public String tag = null;

        /**
        * Optional state to associate with the request
        */
        @ApiMember(Description="Optional state to associate with the request")
        public String state = null;
        
        public String getPositivePrompt() { return positivePrompt; }
        public QueueTextToImage setPositivePrompt(String value) { this.positivePrompt = value; return this; }
        public String getNegativePrompt() { return negativePrompt; }
        public QueueTextToImage setNegativePrompt(String value) { this.negativePrompt = value; return this; }
        public Integer getWidth() { return width; }
        public QueueTextToImage setWidth(Integer value) { this.width = value; return this; }
        public Integer getHeight() { return height; }
        public QueueTextToImage setHeight(Integer value) { this.height = value; return this; }
        public Integer getBatchSize() { return batchSize; }
        public QueueTextToImage setBatchSize(Integer value) { this.batchSize = value; return this; }
        public String getModel() { return model; }
        public QueueTextToImage setModel(String value) { this.model = value; return this; }
        public Integer getSeed() { return seed; }
        public QueueTextToImage setSeed(Integer value) { this.seed = value; return this; }
        public String getRefId() { return refId; }
        public QueueTextToImage setRefId(String value) { this.refId = value; return this; }
        public String getReplyTo() { return replyTo; }
        public QueueTextToImage setReplyTo(String value) { this.replyTo = value; return this; }
        public String getTag() { return tag; }
        public QueueTextToImage setTag(String value) { this.tag = value; return this; }
        public String getState() { return state; }
        public QueueTextToImage setState(String value) { this.state = value; return this; }
        private static Object responseType = QueueGenerationResponse.class;
        public Object getResponseType() { return responseType; }
    }

    /**
    * Upscale an image
    */
    @Api(Description="Upscale an image")
    public static class QueueImageUpscale implements IReturn<QueueGenerationResponse>, IQueueGeneration
    {
        /**
        * The image to upscale
        */
        @ApiMember(Description="The image to upscale")
        @Required()
        public InputStream image = null;

        /**
        * Optional seed for reproducible results in image generation
        */
        @ApiMember(Description="Optional seed for reproducible results in image generation")
        public Integer seed = null;

        /**
        * Optional client-provided identifier for the request
        */
        @ApiMember(Description="Optional client-provided identifier for the request")
        public String refId = null;

        /**
        * Optional queue or topic to reply to
        */
        @ApiMember(Description="Optional queue or topic to reply to")
        public String replyTo = null;

        /**
        * Tag to identify the request
        */
        @ApiMember(Description="Tag to identify the request")
        public String tag = null;

        /**
        * Optional state to associate with the request
        */
        @ApiMember(Description="Optional state to associate with the request")
        public String state = null;
        
        public InputStream getImage() { return image; }
        public QueueImageUpscale setImage(InputStream value) { this.image = value; return this; }
        public Integer getSeed() { return seed; }
        public QueueImageUpscale setSeed(Integer value) { this.seed = value; return this; }
        public String getRefId() { return refId; }
        public QueueImageUpscale setRefId(String value) { this.refId = value; return this; }
        public String getReplyTo() { return replyTo; }
        public QueueImageUpscale setReplyTo(String value) { this.replyTo = value; return this; }
        public String getTag() { return tag; }
        public QueueImageUpscale setTag(String value) { this.tag = value; return this; }
        public String getState() { return state; }
        public QueueImageUpscale setState(String value) { this.state = value; return this; }
        private static Object responseType = QueueGenerationResponse.class;
        public Object getResponseType() { return responseType; }
    }

    /**
    * Generate image from another image
    */
    @Api(Description="Generate image from another image")
    public static class QueueImageToImage implements IReturn<QueueGenerationResponse>, IQueueGeneration
    {
        /**
        * The image to use as input
        */
        @ApiMember(Description="The image to use as input")
        @Required()
        public InputStream image = null;

        /**
        * Prompt describing the desired output
        */
        @ApiMember(Description="Prompt describing the desired output")
        @Validate(Validator="NotEmpty")
        public String positivePrompt = null;

        /**
        * Negative prompt describing what should not be in the image
        */
        @ApiMember(Description="Negative prompt describing what should not be in the image")
        public String negativePrompt = null;

        /**
        * The AI model to use for image generation
        */
        @ApiMember(Description="The AI model to use for image generation")
        public String model = null;

        /**
        * Optional specific amount of denoise to apply
        */
        @ApiMember(Description="Optional specific amount of denoise to apply")
        public Float denoise = null;

        /**
        * Number of images to generate in a single batch
        */
        @ApiMember(Description="Number of images to generate in a single batch")
        public Integer batchSize = null;

        /**
        * Optional seed for reproducible results in image generation
        */
        @ApiMember(Description="Optional seed for reproducible results in image generation")
        public Integer seed = null;

        /**
        * Optional client-provided identifier for the request
        */
        @ApiMember(Description="Optional client-provided identifier for the request")
        public String refId = null;

        /**
        * Optional queue or topic to reply to
        */
        @ApiMember(Description="Optional queue or topic to reply to")
        public String replyTo = null;

        /**
        * Optional state to associate with the request
        */
        @ApiMember(Description="Optional state to associate with the request")
        public String state = null;

        /**
        * Tag to identify the request
        */
        @ApiMember(Description="Tag to identify the request")
        public String tag = null;
        
        public InputStream getImage() { return image; }
        public QueueImageToImage setImage(InputStream value) { this.image = value; return this; }
        public String getPositivePrompt() { return positivePrompt; }
        public QueueImageToImage setPositivePrompt(String value) { this.positivePrompt = value; return this; }
        public String getNegativePrompt() { return negativePrompt; }
        public QueueImageToImage setNegativePrompt(String value) { this.negativePrompt = value; return this; }
        public String getModel() { return model; }
        public QueueImageToImage setModel(String value) { this.model = value; return this; }
        public Float getDenoise() { return denoise; }
        public QueueImageToImage setDenoise(Float value) { this.denoise = value; return this; }
        public Integer getBatchSize() { return batchSize; }
        public QueueImageToImage setBatchSize(Integer value) { this.batchSize = value; return this; }
        public Integer getSeed() { return seed; }
        public QueueImageToImage setSeed(Integer value) { this.seed = value; return this; }
        public String getRefId() { return refId; }
        public QueueImageToImage setRefId(String value) { this.refId = value; return this; }
        public String getReplyTo() { return replyTo; }
        public QueueImageToImage setReplyTo(String value) { this.replyTo = value; return this; }
        public String getState() { return state; }
        public QueueImageToImage setState(String value) { this.state = value; return this; }
        public String getTag() { return tag; }
        public QueueImageToImage setTag(String value) { this.tag = value; return this; }
        private static Object responseType = QueueGenerationResponse.class;
        public Object getResponseType() { return responseType; }
    }

    /**
    * Generate image with masked area
    */
    @Api(Description="Generate image with masked area")
    public static class QueueImageWithMask implements IReturn<QueueGenerationResponse>, IQueueGeneration
    {
        /**
        * Prompt describing the desired output in the masked area
        */
        @ApiMember(Description="Prompt describing the desired output in the masked area")
        @Validate(Validator="NotEmpty")
        public String positivePrompt = null;

        /**
        * Negative prompt describing what should not be in the masked area
        */
        @ApiMember(Description="Negative prompt describing what should not be in the masked area")
        public String negativePrompt = null;

        /**
        * The image to use as input
        */
        @ApiMember(Description="The image to use as input")
        @Required()
        public InputStream image = null;

        /**
        * The mask to use as input
        */
        @ApiMember(Description="The mask to use as input")
        @Required()
        public InputStream mask = null;

        /**
        * Optional specific amount of denoise to apply
        */
        @ApiMember(Description="Optional specific amount of denoise to apply")
        public Float denoise = null;

        /**
        * Optional seed for reproducible results in image generation
        */
        @ApiMember(Description="Optional seed for reproducible results in image generation")
        public Integer seed = null;

        /**
        * Optional client-provided identifier for the request
        */
        @ApiMember(Description="Optional client-provided identifier for the request")
        public String refId = null;

        /**
        * Optional queue or topic to reply to
        */
        @ApiMember(Description="Optional queue or topic to reply to")
        public String replyTo = null;

        /**
        * Tag to identify the request
        */
        @ApiMember(Description="Tag to identify the request")
        public String tag = null;

        /**
        * Optional state to associate with the request
        */
        @ApiMember(Description="Optional state to associate with the request")
        public String state = null;
        
        public String getPositivePrompt() { return positivePrompt; }
        public QueueImageWithMask setPositivePrompt(String value) { this.positivePrompt = value; return this; }
        public String getNegativePrompt() { return negativePrompt; }
        public QueueImageWithMask setNegativePrompt(String value) { this.negativePrompt = value; return this; }
        public InputStream getImage() { return image; }
        public QueueImageWithMask setImage(InputStream value) { this.image = value; return this; }
        public InputStream getMask() { return mask; }
        public QueueImageWithMask setMask(InputStream value) { this.mask = value; return this; }
        public Float getDenoise() { return denoise; }
        public QueueImageWithMask setDenoise(Float value) { this.denoise = value; return this; }
        public Integer getSeed() { return seed; }
        public QueueImageWithMask setSeed(Integer value) { this.seed = value; return this; }
        public String getRefId() { return refId; }
        public QueueImageWithMask setRefId(String value) { this.refId = value; return this; }
        public String getReplyTo() { return replyTo; }
        public QueueImageWithMask setReplyTo(String value) { this.replyTo = value; return this; }
        public String getTag() { return tag; }
        public QueueImageWithMask setTag(String value) { this.tag = value; return this; }
        public String getState() { return state; }
        public QueueImageWithMask setState(String value) { this.state = value; return this; }
        private static Object responseType = QueueGenerationResponse.class;
        public Object getResponseType() { return responseType; }
    }

    /**
    * Convert image to text
    */
    @Api(Description="Convert image to text")
    public static class QueueImageToText implements IReturn<QueueGenerationResponse>, IQueueGeneration
    {
        /**
        * The image to convert to text
        */
        @ApiMember(Description="The image to convert to text")
        @Required()
        public InputStream image = null;

        /**
        * Optional client-provided identifier for the request
        */
        @ApiMember(Description="Optional client-provided identifier for the request")
        public String refId = null;

        /**
        * Optional queue or topic to reply to
        */
        @ApiMember(Description="Optional queue or topic to reply to")
        public String replyTo = null;

        /**
        * Tag to identify the request
        */
        @ApiMember(Description="Tag to identify the request")
        public String tag = null;

        /**
        * Optional state to associate with the request
        */
        @ApiMember(Description="Optional state to associate with the request")
        public String state = null;
        
        public InputStream getImage() { return image; }
        public QueueImageToText setImage(InputStream value) { this.image = value; return this; }
        public String getRefId() { return refId; }
        public QueueImageToText setRefId(String value) { this.refId = value; return this; }
        public String getReplyTo() { return replyTo; }
        public QueueImageToText setReplyTo(String value) { this.replyTo = value; return this; }
        public String getTag() { return tag; }
        public QueueImageToText setTag(String value) { this.tag = value; return this; }
        public String getState() { return state; }
        public QueueImageToText setState(String value) { this.state = value; return this; }
        private static Object responseType = QueueGenerationResponse.class;
        public Object getResponseType() { return responseType; }
    }

    /**
    * Convert an image to a different format
    */
    public static class ConvertImage implements IReturn<MediaTransformResponse>, IMediaTransform, IPost
    {
        /**
        * The image file to be converted
        */
        @ApiMember(Description="The image file to be converted")
        @Required()
        public InputStream image = null;

        /**
        * The desired output format for the converted image
        */
        @ApiMember(Description="The desired output format for the converted image")
        @Required()
        public ImageOutputFormat outputFormat = null;

        /**
        * Optional client-provided identifier for the request
        */
        @ApiMember(Description="Optional client-provided identifier for the request")
        public String refId = null;

        /**
        * Tag to identify the request
        */
        @ApiMember(Description="Tag to identify the request")
        public String tag = null;
        
        public InputStream getImage() { return image; }
        public ConvertImage setImage(InputStream value) { this.image = value; return this; }
        public ImageOutputFormat getOutputFormat() { return outputFormat; }
        public ConvertImage setOutputFormat(ImageOutputFormat value) { this.outputFormat = value; return this; }
        public String getRefId() { return refId; }
        public ConvertImage setRefId(String value) { this.refId = value; return this; }
        public String getTag() { return tag; }
        public ConvertImage setTag(String value) { this.tag = value; return this; }
        private static Object responseType = MediaTransformResponse.class;
        public Object getResponseType() { return responseType; }
    }

    /**
    * Crop an image to a specified area
    */
    public static class CropImage implements IReturn<MediaTransformResponse>, IMediaTransform, IPost
    {
        /**
        * The X-coordinate of the top-left corner of the crop area
        */
        @ApiMember(Description="The X-coordinate of the top-left corner of the crop area")
        public Integer x = null;

        /**
        * The Y-coordinate of the top-left corner of the crop area
        */
        @ApiMember(Description="The Y-coordinate of the top-left corner of the crop area")
        public Integer y = null;

        /**
        * The width of the crop area
        */
        @ApiMember(Description="The width of the crop area")
        public Integer width = null;

        /**
        * The height of the crop area
        */
        @ApiMember(Description="The height of the crop area")
        public Integer height = null;

        /**
        * The image file to be cropped
        */
        @ApiMember(Description="The image file to be cropped")
        @Required()
        public InputStream image = null;

        /**
        * Optional client-provided identifier for the request
        */
        @ApiMember(Description="Optional client-provided identifier for the request")
        public String refId = null;

        /**
        * Tag to identify the request
        */
        @ApiMember(Description="Tag to identify the request")
        public String tag = null;
        
        public Integer getX() { return x; }
        public CropImage setX(Integer value) { this.x = value; return this; }
        public Integer getY() { return y; }
        public CropImage setY(Integer value) { this.y = value; return this; }
        public Integer getWidth() { return width; }
        public CropImage setWidth(Integer value) { this.width = value; return this; }
        public Integer getHeight() { return height; }
        public CropImage setHeight(Integer value) { this.height = value; return this; }
        public InputStream getImage() { return image; }
        public CropImage setImage(InputStream value) { this.image = value; return this; }
        public String getRefId() { return refId; }
        public CropImage setRefId(String value) { this.refId = value; return this; }
        public String getTag() { return tag; }
        public CropImage setTag(String value) { this.tag = value; return this; }
        private static Object responseType = MediaTransformResponse.class;
        public Object getResponseType() { return responseType; }
    }

    /**
    * Add a watermark to an image
    */
    public static class WatermarkImage implements IReturn<MediaTransformResponse>, IMediaTransform, IPost
    {
        /**
        * The image file to be watermarked
        */
        @ApiMember(Description="The image file to be watermarked")
        @Required()
        public InputStream image = null;

        /**
        * The position of the watermark on the image
        */
        @ApiMember(Description="The position of the watermark on the image")
        public WatermarkPosition position = null;

        /**
        * Scale of the watermark relative
        */
        @ApiMember(Description="Scale of the watermark relative")
        public Float watermarkScale = null;

        /**
        * The opacity of the watermark (0.0 to 1.0)
        */
        @ApiMember(Description="The opacity of the watermark (0.0 to 1.0)")
        public Float opacity = null;

        /**
        * Optional client-provided identifier for the request
        */
        @ApiMember(Description="Optional client-provided identifier for the request")
        public String refId = null;

        /**
        * Tag to identify the request
        */
        @ApiMember(Description="Tag to identify the request")
        public String tag = null;
        
        public InputStream getImage() { return image; }
        public WatermarkImage setImage(InputStream value) { this.image = value; return this; }
        public WatermarkPosition getPosition() { return position; }
        public WatermarkImage setPosition(WatermarkPosition value) { this.position = value; return this; }
        public Float getWatermarkScale() { return watermarkScale; }
        public WatermarkImage setWatermarkScale(Float value) { this.watermarkScale = value; return this; }
        public Float getOpacity() { return opacity; }
        public WatermarkImage setOpacity(Float value) { this.opacity = value; return this; }
        public String getRefId() { return refId; }
        public WatermarkImage setRefId(String value) { this.refId = value; return this; }
        public String getTag() { return tag; }
        public WatermarkImage setTag(String value) { this.tag = value; return this; }
        private static Object responseType = MediaTransformResponse.class;
        public Object getResponseType() { return responseType; }
    }

    /**
    * Scale an image to a specified size
    */
    public static class ScaleImage implements IReturn<MediaTransformResponse>, IMediaTransform, IPost
    {
        /**
        * The image file to be scaled
        */
        @ApiMember(Description="The image file to be scaled")
        @Required()
        public InputStream image = null;

        /**
        * Desired width of the scaled image
        */
        @ApiMember(Description="Desired width of the scaled image")
        public Integer width = null;

        /**
        * Desired height of the scaled image
        */
        @ApiMember(Description="Desired height of the scaled image")
        public Integer height = null;

        /**
        * Optional client-provided identifier for the request
        */
        @ApiMember(Description="Optional client-provided identifier for the request")
        public String refId = null;

        /**
        * Tag to identify the request
        */
        @ApiMember(Description="Tag to identify the request")
        public String tag = null;
        
        public InputStream getImage() { return image; }
        public ScaleImage setImage(InputStream value) { this.image = value; return this; }
        public Integer getWidth() { return width; }
        public ScaleImage setWidth(Integer value) { this.width = value; return this; }
        public Integer getHeight() { return height; }
        public ScaleImage setHeight(Integer value) { this.height = value; return this; }
        public String getRefId() { return refId; }
        public ScaleImage setRefId(String value) { this.refId = value; return this; }
        public String getTag() { return tag; }
        public ScaleImage setTag(String value) { this.tag = value; return this; }
        private static Object responseType = MediaTransformResponse.class;
        public Object getResponseType() { return responseType; }
    }

    /**
    * Crop an image to a specified area
    */
    public static class QueueCropImage implements IReturn<QueueMediaTransformResponse>, IQueueMediaTransform, IPost
    {
        /**
        * The X-coordinate of the top-left corner of the crop area
        */
        @ApiMember(Description="The X-coordinate of the top-left corner of the crop area")
        public Integer x = null;

        /**
        * The Y-coordinate of the top-left corner of the crop area
        */
        @ApiMember(Description="The Y-coordinate of the top-left corner of the crop area")
        public Integer y = null;

        /**
        * The width of the crop area
        */
        @ApiMember(Description="The width of the crop area")
        public Integer width = null;

        /**
        * The height of the crop area
        */
        @ApiMember(Description="The height of the crop area")
        public Integer height = null;

        /**
        * The image file to be cropped
        */
        @ApiMember(Description="The image file to be cropped")
        @Required()
        public InputStream image = null;

        /**
        * Optional client-provided identifier for the request
        */
        @ApiMember(Description="Optional client-provided identifier for the request")
        public String refId = null;

        /**
        * Optional queue or topic to reply to
        */
        @ApiMember(Description="Optional queue or topic to reply to")
        public String replyTo = null;

        /**
        * Tag to identify the request
        */
        @ApiMember(Description="Tag to identify the request")
        public String tag = null;
        
        public Integer getX() { return x; }
        public QueueCropImage setX(Integer value) { this.x = value; return this; }
        public Integer getY() { return y; }
        public QueueCropImage setY(Integer value) { this.y = value; return this; }
        public Integer getWidth() { return width; }
        public QueueCropImage setWidth(Integer value) { this.width = value; return this; }
        public Integer getHeight() { return height; }
        public QueueCropImage setHeight(Integer value) { this.height = value; return this; }
        public InputStream getImage() { return image; }
        public QueueCropImage setImage(InputStream value) { this.image = value; return this; }
        public String getRefId() { return refId; }
        public QueueCropImage setRefId(String value) { this.refId = value; return this; }
        public String getReplyTo() { return replyTo; }
        public QueueCropImage setReplyTo(String value) { this.replyTo = value; return this; }
        public String getTag() { return tag; }
        public QueueCropImage setTag(String value) { this.tag = value; return this; }
        private static Object responseType = QueueMediaTransformResponse.class;
        public Object getResponseType() { return responseType; }
    }

    /**
    * Scale an image to a specified size
    */
    public static class QueueScaleImage implements IReturn<MediaTransformResponse>, IQueueMediaTransform, IPost
    {
        /**
        * The image file to be scaled
        */
        @ApiMember(Description="The image file to be scaled")
        @Required()
        public InputStream image = null;

        /**
        * Desired width of the scaled image
        */
        @ApiMember(Description="Desired width of the scaled image")
        public Integer width = null;

        /**
        * Desired height of the scaled image
        */
        @ApiMember(Description="Desired height of the scaled image")
        public Integer height = null;

        /**
        * Optional client-provided identifier for the request
        */
        @ApiMember(Description="Optional client-provided identifier for the request")
        public String refId = null;

        /**
        * Optional queue or topic to reply to
        */
        @ApiMember(Description="Optional queue or topic to reply to")
        public String replyTo = null;

        /**
        * Tag to identify the request
        */
        @ApiMember(Description="Tag to identify the request")
        public String tag = null;
        
        public InputStream getImage() { return image; }
        public QueueScaleImage setImage(InputStream value) { this.image = value; return this; }
        public Integer getWidth() { return width; }
        public QueueScaleImage setWidth(Integer value) { this.width = value; return this; }
        public Integer getHeight() { return height; }
        public QueueScaleImage setHeight(Integer value) { this.height = value; return this; }
        public String getRefId() { return refId; }
        public QueueScaleImage setRefId(String value) { this.refId = value; return this; }
        public String getReplyTo() { return replyTo; }
        public QueueScaleImage setReplyTo(String value) { this.replyTo = value; return this; }
        public String getTag() { return tag; }
        public QueueScaleImage setTag(String value) { this.tag = value; return this; }
        private static Object responseType = MediaTransformResponse.class;
        public Object getResponseType() { return responseType; }
    }

    /**
    * Add a watermark to an image
    */
    public static class QueueWatermarkImage implements IReturn<QueueMediaTransformResponse>, IQueueMediaTransform, IPost
    {
        /**
        * The image file to be watermarked
        */
        @ApiMember(Description="The image file to be watermarked")
        @Required()
        public InputStream image = null;

        /**
        * The position of the watermark on the image
        */
        @ApiMember(Description="The position of the watermark on the image")
        public WatermarkPosition position = null;

        /**
        * The opacity of the watermark (0.0 to 1.0)
        */
        @ApiMember(Description="The opacity of the watermark (0.0 to 1.0)")
        public Float opacity = null;

        /**
        * Scale of the watermark relative
        */
        @ApiMember(Description="Scale of the watermark relative")
        public Float watermarkScale = null;

        /**
        * Optional client-provided identifier for the request
        */
        @ApiMember(Description="Optional client-provided identifier for the request")
        public String refId = null;

        /**
        * Optional queue or topic to reply to
        */
        @ApiMember(Description="Optional queue or topic to reply to")
        public String replyTo = null;

        /**
        * Tag to identify the request
        */
        @ApiMember(Description="Tag to identify the request")
        public String tag = null;
        
        public InputStream getImage() { return image; }
        public QueueWatermarkImage setImage(InputStream value) { this.image = value; return this; }
        public WatermarkPosition getPosition() { return position; }
        public QueueWatermarkImage setPosition(WatermarkPosition value) { this.position = value; return this; }
        public Float getOpacity() { return opacity; }
        public QueueWatermarkImage setOpacity(Float value) { this.opacity = value; return this; }
        public Float getWatermarkScale() { return watermarkScale; }
        public QueueWatermarkImage setWatermarkScale(Float value) { this.watermarkScale = value; return this; }
        public String getRefId() { return refId; }
        public QueueWatermarkImage setRefId(String value) { this.refId = value; return this; }
        public String getReplyTo() { return replyTo; }
        public QueueWatermarkImage setReplyTo(String value) { this.replyTo = value; return this; }
        public String getTag() { return tag; }
        public QueueWatermarkImage setTag(String value) { this.tag = value; return this; }
        private static Object responseType = QueueMediaTransformResponse.class;
        public Object getResponseType() { return responseType; }
    }

    /**
    * Convert an image to a different format
    */
    public static class QueueConvertImage implements IReturn<QueueMediaTransformResponse>, IQueueMediaTransform, IPost
    {
        /**
        * The image file to be converted
        */
        @ApiMember(Description="The image file to be converted")
        @Required()
        public InputStream image = null;

        /**
        * The desired output format for the converted image
        */
        @ApiMember(Description="The desired output format for the converted image")
        @Required()
        public ImageOutputFormat outputFormat = null;

        /**
        * Optional client-provided identifier for the request
        */
        @ApiMember(Description="Optional client-provided identifier for the request")
        public String refId = null;

        /**
        * Optional queue or topic to reply to
        */
        @ApiMember(Description="Optional queue or topic to reply to")
        public String replyTo = null;

        /**
        * Tag to identify the request
        */
        @ApiMember(Description="Tag to identify the request")
        public String tag = null;
        
        public InputStream getImage() { return image; }
        public QueueConvertImage setImage(InputStream value) { this.image = value; return this; }
        public ImageOutputFormat getOutputFormat() { return outputFormat; }
        public QueueConvertImage setOutputFormat(ImageOutputFormat value) { this.outputFormat = value; return this; }
        public String getRefId() { return refId; }
        public QueueConvertImage setRefId(String value) { this.refId = value; return this; }
        public String getReplyTo() { return replyTo; }
        public QueueConvertImage setReplyTo(String value) { this.replyTo = value; return this; }
        public String getTag() { return tag; }
        public QueueConvertImage setTag(String value) { this.tag = value; return this; }
        private static Object responseType = QueueMediaTransformResponse.class;
        public Object getResponseType() { return responseType; }
    }

    public static class QueryMediaTypes extends QueryDb<MediaType> implements IReturn<QueryResponse<MediaType>>
    {
        
        private static Object responseType = new TypeToken<QueryResponse<MediaType>>(){}.getType();
        public Object getResponseType() { return responseType; }
    }

    /**
    * Media Providers
    */
    public static class QueryMediaProviders extends QueryDb<MediaProvider> implements IReturn<QueryResponse<MediaProvider>>
    {
        public Integer id = null;
        public String name = null;
        
        public Integer getId() { return id; }
        public QueryMediaProviders setId(Integer value) { this.id = value; return this; }
        public String getName() { return name; }
        public QueryMediaProviders setName(String value) { this.name = value; return this; }
        private static Object responseType = new TypeToken<QueryResponse<MediaProvider>>(){}.getType();
        public Object getResponseType() { return responseType; }
    }

    /**
    * Text to Speech Voice models
    */
    public static class QueryTextToSpeechVoices extends QueryDb<TextToSpeechVoice> implements IReturn<QueryResponse<TextToSpeechVoice>>
    {
        
        private static Object responseType = new TypeToken<QueryResponse<TextToSpeechVoice>>(){}.getType();
        public Object getResponseType() { return responseType; }
    }

    @Route(Path="/generate", Verbs="POST")
    public static class CreateGeneration implements IReturn<CreateGenerationResponse>
    {
        @Validate(Validator="NotNull")
        public GenerationArgs request = null;

        public String provider = null;
        public String state = null;
        public String replyTo = null;
        public String refId = null;
        
        public GenerationArgs getRequest() { return request; }
        public CreateGeneration setRequest(GenerationArgs value) { this.request = value; return this; }
        public String getProvider() { return provider; }
        public CreateGeneration setProvider(String value) { this.provider = value; return this; }
        public String getState() { return state; }
        public CreateGeneration setState(String value) { this.state = value; return this; }
        public String getReplyTo() { return replyTo; }
        public CreateGeneration setReplyTo(String value) { this.replyTo = value; return this; }
        public String getRefId() { return refId; }
        public CreateGeneration setRefId(String value) { this.refId = value; return this; }
        private static Object responseType = CreateGenerationResponse.class;
        public Object getResponseType() { return responseType; }
    }

    /**
    * Media Models
    */
    public static class QueryMediaModels extends QueryDb<MediaModel> implements IReturn<QueryResponse<MediaModel>>
    {
        public String id = null;
        
        public String getId() { return id; }
        public QueryMediaModels setId(String value) { this.id = value; return this; }
        private static Object responseType = new TypeToken<QueryResponse<MediaModel>>(){}.getType();
        public Object getResponseType() { return responseType; }
    }

    @Route(Path="/generation/{Id}", Verbs="GET")
    // @Route(Path="/generation/ref/{RefId}", Verbs="GET")
    public static class GetGeneration implements IReturn<GetGenerationResponse>
    {
        public Integer id = null;
        public String refId = null;
        
        public Integer getId() { return id; }
        public GetGeneration setId(Integer value) { this.id = value; return this; }
        public String getRefId() { return refId; }
        public GetGeneration setRefId(String value) { this.refId = value; return this; }
        private static Object responseType = GetGenerationResponse.class;
        public Object getResponseType() { return responseType; }
    }

    /**
    * Update a Generation API Provider
    */
    public static class UpdateMediaProvider implements IReturn<IdResponse>, IPatchDb<MediaProvider>
    {
        public Integer id = null;
        /**
        * The API Key to use for this Provider
        */
        public String apiKey = null;
        /**
        * Send the API Key in the Header instead of Authorization Bearer
        */
        public String apiKeyHeader = null;
        /**
        * Override Base URL for the Generation Provider
        */
        public String apiBaseUrl = null;
        /**
        * Url to check if the API is online
        */
        public String heartbeatUrl = null;
        /**
        * How many requests should be made concurrently
        */
        public Integer concurrency = null;
        /**
        * What priority to give this Provider to use for processing models
        */
        public Integer priority = null;
        /**
        * Whether the Provider is enabled
        */
        public Boolean enabled = null;
        /**
        * The models this API Provider should process
        */
        public ArrayList<String> models = null;
        
        public Integer getId() { return id; }
        public UpdateMediaProvider setId(Integer value) { this.id = value; return this; }
        public String getApiKey() { return apiKey; }
        public UpdateMediaProvider setApiKey(String value) { this.apiKey = value; return this; }
        public String getApiKeyHeader() { return apiKeyHeader; }
        public UpdateMediaProvider setApiKeyHeader(String value) { this.apiKeyHeader = value; return this; }
        public String getApiBaseUrl() { return apiBaseUrl; }
        public UpdateMediaProvider setApiBaseUrl(String value) { this.apiBaseUrl = value; return this; }
        public String getHeartbeatUrl() { return heartbeatUrl; }
        public UpdateMediaProvider setHeartbeatUrl(String value) { this.heartbeatUrl = value; return this; }
        public Integer getConcurrency() { return concurrency; }
        public UpdateMediaProvider setConcurrency(Integer value) { this.concurrency = value; return this; }
        public Integer getPriority() { return priority; }
        public UpdateMediaProvider setPriority(Integer value) { this.priority = value; return this; }
        public Boolean isEnabled() { return enabled; }
        public UpdateMediaProvider setEnabled(Boolean value) { this.enabled = value; return this; }
        public ArrayList<String> getModels() { return models; }
        public UpdateMediaProvider setModels(ArrayList<String> value) { this.models = value; return this; }
        private static Object responseType = IdResponse.class;
        public Object getResponseType() { return responseType; }
    }

    /**
    * Add an API Provider to Generation API Providers
    */
    public static class CreateMediaProvider implements IReturn<IdResponse>, ICreateDb<MediaProvider>
    {
        /**
        * The name of the API Provider
        */
        public String name = null;
        /**
        * The API Key to use for this Provider
        */
        public String apiKey = null;
        /**
        * Send the API Key in the Header instead of Authorization Bearer
        */
        public String apiKeyHeader = null;
        /**
        * Base URL for the Generation Provider
        */
        public String apiBaseUrl = null;
        /**
        * Url to check if the API is online
        */
        public String heartbeatUrl = null;
        /**
        * How many requests should be made concurrently
        */
        public Integer concurrency = null;
        /**
        * What priority to give this Provider to use for processing models
        */
        public Integer priority = null;
        /**
        * Whether the Provider is enabled
        */
        public Boolean enabled = null;
        /**
        * The date the Provider was last online
        */
        public Date offlineDate = null;
        /**
        * Models this API Provider should process
        */
        public ArrayList<String> models = null;
        public String mediaTypeId = null;
        
        public String getName() { return name; }
        public CreateMediaProvider setName(String value) { this.name = value; return this; }
        public String getApiKey() { return apiKey; }
        public CreateMediaProvider setApiKey(String value) { this.apiKey = value; return this; }
        public String getApiKeyHeader() { return apiKeyHeader; }
        public CreateMediaProvider setApiKeyHeader(String value) { this.apiKeyHeader = value; return this; }
        public String getApiBaseUrl() { return apiBaseUrl; }
        public CreateMediaProvider setApiBaseUrl(String value) { this.apiBaseUrl = value; return this; }
        public String getHeartbeatUrl() { return heartbeatUrl; }
        public CreateMediaProvider setHeartbeatUrl(String value) { this.heartbeatUrl = value; return this; }
        public Integer getConcurrency() { return concurrency; }
        public CreateMediaProvider setConcurrency(Integer value) { this.concurrency = value; return this; }
        public Integer getPriority() { return priority; }
        public CreateMediaProvider setPriority(Integer value) { this.priority = value; return this; }
        public Boolean isEnabled() { return enabled; }
        public CreateMediaProvider setEnabled(Boolean value) { this.enabled = value; return this; }
        public Date getOfflineDate() { return offlineDate; }
        public CreateMediaProvider setOfflineDate(Date value) { this.offlineDate = value; return this; }
        public ArrayList<String> getModels() { return models; }
        public CreateMediaProvider setModels(ArrayList<String> value) { this.models = value; return this; }
        public String getMediaTypeId() { return mediaTypeId; }
        public CreateMediaProvider setMediaTypeId(String value) { this.mediaTypeId = value; return this; }
        private static Object responseType = IdResponse.class;
        public Object getResponseType() { return responseType; }
    }

    public static class CreateMediaTransform implements IReturn<CreateTransformResponse>
    {
        @Validate(Validator="NotNull")
        public MediaTransformArgs request = null;

        public String provider = null;
        public String state = null;
        public String replyTo = null;
        public String refId = null;
        
        public MediaTransformArgs getRequest() { return request; }
        public CreateMediaTransform setRequest(MediaTransformArgs value) { this.request = value; return this; }
        public String getProvider() { return provider; }
        public CreateMediaTransform setProvider(String value) { this.provider = value; return this; }
        public String getState() { return state; }
        public CreateMediaTransform setState(String value) { this.state = value; return this; }
        public String getReplyTo() { return replyTo; }
        public CreateMediaTransform setReplyTo(String value) { this.replyTo = value; return this; }
        public String getRefId() { return refId; }
        public CreateMediaTransform setRefId(String value) { this.refId = value; return this; }
        private static Object responseType = CreateTransformResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route(Path="/hello/{Name}")
    public static class Hello implements IReturn<HelloResponse>, IGet
    {
        public String name = null;
        
        public String getName() { return name; }
        public Hello setName(String value) { this.name = value; return this; }
        private static Object responseType = HelloResponse.class;
        public Object getResponseType() { return responseType; }
    }

    public static class GetOllamaModels implements IReturn<GetOllamaModelsResponse>, IGet
    {
        @Validate(Validator="NotEmpty")
        public String apiBaseUrl = null;
        
        public String getApiBaseUrl() { return apiBaseUrl; }
        public GetOllamaModels setApiBaseUrl(String value) { this.apiBaseUrl = value; return this; }
        private static Object responseType = GetOllamaModelsResponse.class;
        public Object getResponseType() { return responseType; }
    }

    /**
    * Different Models available in AI Server
    */
    public static class QueryAiModels extends QueryDb<AiModel> implements IReturn<QueryResponse<AiModel>>
    {
        
        private static Object responseType = new TypeToken<QueryResponse<AiModel>>(){}.getType();
        public Object getResponseType() { return responseType; }
    }

    /**
    * The Type and behavior of different API Providers
    */
    public static class QueryAiTypes extends QueryDb<AiType> implements IReturn<QueryResponse<AiType>>
    {
        
        private static Object responseType = new TypeToken<QueryResponse<AiType>>(){}.getType();
        public Object getResponseType() { return responseType; }
    }

    /**
    * Active AI Worker Models available in AI Server
    */
    public static class ActiveAiModels implements IReturn<StringsResponse>, IGet
    {
        
        private static Object responseType = StringsResponse.class;
        public Object getResponseType() { return responseType; }
    }

    /**
    * AI Providers
    */
    public static class QueryAiProviders extends QueryDb<AiProvider> implements IReturn<QueryResponse<AiProvider>>
    {
        public String name = null;
        
        public String getName() { return name; }
        public QueryAiProviders setName(String value) { this.name = value; return this; }
        private static Object responseType = new TypeToken<QueryResponse<AiProvider>>(){}.getType();
        public Object getResponseType() { return responseType; }
    }

    public static class GetWorkerStats implements IReturn<GetWorkerStatsResponse>, IGet
    {
        
        private static Object responseType = GetWorkerStatsResponse.class;
        public Object getResponseType() { return responseType; }
    }

    public static class CancelWorker implements IReturn<EmptyResponse>
    {
        @Validate(Validator="NotEmpty")
        public String worker = null;
        
        public String getWorker() { return worker; }
        public CancelWorker setWorker(String value) { this.worker = value; return this; }
        private static Object responseType = EmptyResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route(Path="/icons/models/{Model}", Verbs="GET")
    public static class GetModelImage implements IReturn<byte[]>, IGet
    {
        public String model = null;
        
        public String getModel() { return model; }
        public GetModelImage setModel(String value) { this.model = value; return this; }
        private static Object responseType = byte[].class;
        public Object getResponseType() { return responseType; }
    }

    /**
    * Given a list of messages comprising a conversation, the model will return a response.
    */
    @Route(Path="/v1/chat/completions", Verbs="POST")
    public static class OpenAiChatCompletion extends OpenAiChat implements IReturn<OpenAiChatResponse>, IPost
    {
        /**
        * Provide a unique identifier to track requests
        */
        public String refId = null;
        /**
        * Specify which AI Provider to use
        */
        public String provider = null;
        /**
        * Categorize like requests under a common group
        */
        public String tag = null;
        
        public String getRefId() { return refId; }
        public OpenAiChatCompletion setRefId(String value) { this.refId = value; return this; }
        public String getProvider() { return provider; }
        public OpenAiChatCompletion setProvider(String value) { this.provider = value; return this; }
        public String getTag() { return tag; }
        public OpenAiChatCompletion setTag(String value) { this.tag = value; return this; }
        private static Object responseType = OpenAiChatResponse.class;
        public Object getResponseType() { return responseType; }
    }

    public static class QueueOpenAiChatCompletion implements IReturn<QueueOpenAiChatResponse>
    {
        public String refId = null;
        public String provider = null;
        public String replyTo = null;
        public String tag = null;
        public OpenAiChat request = null;
        
        public String getRefId() { return refId; }
        public QueueOpenAiChatCompletion setRefId(String value) { this.refId = value; return this; }
        public String getProvider() { return provider; }
        public QueueOpenAiChatCompletion setProvider(String value) { this.provider = value; return this; }
        public String getReplyTo() { return replyTo; }
        public QueueOpenAiChatCompletion setReplyTo(String value) { this.replyTo = value; return this; }
        public String getTag() { return tag; }
        public QueueOpenAiChatCompletion setTag(String value) { this.tag = value; return this; }
        public OpenAiChat getRequest() { return request; }
        public QueueOpenAiChatCompletion setRequest(OpenAiChat value) { this.request = value; return this; }
        private static Object responseType = QueueOpenAiChatResponse.class;
        public Object getResponseType() { return responseType; }
    }

    public static class WaitForOpenAiChat implements IReturn<GetOpenAiChatResponse>, IGet
    {
        public Integer id = null;
        public String refId = null;
        
        public Integer getId() { return id; }
        public WaitForOpenAiChat setId(Integer value) { this.id = value; return this; }
        public String getRefId() { return refId; }
        public WaitForOpenAiChat setRefId(String value) { this.refId = value; return this; }
        private static Object responseType = GetOpenAiChatResponse.class;
        public Object getResponseType() { return responseType; }
    }

    public static class GetOpenAiChat implements IReturn<GetOpenAiChatResponse>, IGet
    {
        public Integer id = null;
        public String refId = null;
        
        public Integer getId() { return id; }
        public GetOpenAiChat setId(Integer value) { this.id = value; return this; }
        public String getRefId() { return refId; }
        public GetOpenAiChat setRefId(String value) { this.refId = value; return this; }
        private static Object responseType = GetOpenAiChatResponse.class;
        public Object getResponseType() { return responseType; }
    }

    public static class GetOpenAiChatStatus implements IReturn<GetOpenAiChatStatusResponse>, IGet
    {
        public Long jobId = null;
        public String refId = null;
        
        public Long getJobId() { return jobId; }
        public GetOpenAiChatStatus setJobId(Long value) { this.jobId = value; return this; }
        public String getRefId() { return refId; }
        public GetOpenAiChatStatus setRefId(String value) { this.refId = value; return this; }
        private static Object responseType = GetOpenAiChatStatusResponse.class;
        public Object getResponseType() { return responseType; }
    }

    public static class GetActiveProviders implements IReturn<GetActiveProvidersResponse>, IGet
    {
        
        private static Object responseType = GetActiveProvidersResponse.class;
        public Object getResponseType() { return responseType; }
    }

    public static class ChatAiProvider implements IReturn<OpenAiChatResponse>, IPost
    {
        public String provider = null;
        public String model = null;
        public OpenAiChat request = null;
        public String prompt = null;
        
        public String getProvider() { return provider; }
        public ChatAiProvider setProvider(String value) { this.provider = value; return this; }
        public String getModel() { return model; }
        public ChatAiProvider setModel(String value) { this.model = value; return this; }
        public OpenAiChat getRequest() { return request; }
        public ChatAiProvider setRequest(OpenAiChat value) { this.request = value; return this; }
        public String getPrompt() { return prompt; }
        public ChatAiProvider setPrompt(String value) { this.prompt = value; return this; }
        private static Object responseType = OpenAiChatResponse.class;
        public Object getResponseType() { return responseType; }
    }

    public static class CreateApiKey implements IReturn<CreateApiKeyResponse>, IPost
    {
        public String key = null;
        public String name = null;
        public String userId = null;
        public String userName = null;
        public ArrayList<String> scopes = null;
        public String notes = null;
        public Integer refId = null;
        public String refIdStr = null;
        public HashMap<String,String> meta = null;
        
        public String getKey() { return key; }
        public CreateApiKey setKey(String value) { this.key = value; return this; }
        public String getName() { return name; }
        public CreateApiKey setName(String value) { this.name = value; return this; }
        public String getUserId() { return userId; }
        public CreateApiKey setUserId(String value) { this.userId = value; return this; }
        public String getUserName() { return userName; }
        public CreateApiKey setUserName(String value) { this.userName = value; return this; }
        public ArrayList<String> getScopes() { return scopes; }
        public CreateApiKey setScopes(ArrayList<String> value) { this.scopes = value; return this; }
        public String getNotes() { return notes; }
        public CreateApiKey setNotes(String value) { this.notes = value; return this; }
        public Integer getRefId() { return refId; }
        public CreateApiKey setRefId(Integer value) { this.refId = value; return this; }
        public String getRefIdStr() { return refIdStr; }
        public CreateApiKey setRefIdStr(String value) { this.refIdStr = value; return this; }
        public HashMap<String,String> getMeta() { return meta; }
        public CreateApiKey setMeta(HashMap<String,String> value) { this.meta = value; return this; }
        private static Object responseType = CreateApiKeyResponse.class;
        public Object getResponseType() { return responseType; }
    }

    /**
    * Add an AI Provider to process AI Requests
    */
    public static class CreateAiProvider implements IReturn<IdResponse>, ICreateDb<AiProvider>
    {
        /**
        * The Type of this API Provider
        */
        @Validate(Validator="GreaterThan(0)")
        public String aiTypeId = null;

        /**
        * The Base URL for the API Provider
        */
        public String apiBaseUrl = null;
        /**
        * The unique name for this API Provider
        */
        @Validate(Validator="NotEmpty")
        public String name = null;

        /**
        * The API Key to use for this Provider
        */
        public String apiKeyVar = null;
        /**
        * The API Key to use for this Provider
        */
        public String apiKey = null;
        /**
        * Send the API Key in the Header instead of Authorization Bearer
        */
        public String apiKeyHeader = null;
        /**
        * The URL to check if the API Provider is still online
        */
        public String heartbeatUrl = null;
        /**
        * Override API Paths for different AI Requests
        */
        public HashMap<TaskType,String> taskPaths = null;
        /**
        * How many requests should be made concurrently
        */
        public Integer concurrency = null;
        /**
        * What priority to give this Provider to use for processing models
        */
        public Integer priority = null;
        /**
        * Whether the Provider is enabled
        */
        public Boolean enabled = null;
        /**
        * The models this API Provider should process
        */
        public ArrayList<AiProviderModel> models = null;
        /**
        * The selected models this API Provider should process
        */
        public ArrayList<String> selectedModels = null;
        
        public String getAiTypeId() { return aiTypeId; }
        public CreateAiProvider setAiTypeId(String value) { this.aiTypeId = value; return this; }
        public String getApiBaseUrl() { return apiBaseUrl; }
        public CreateAiProvider setApiBaseUrl(String value) { this.apiBaseUrl = value; return this; }
        public String getName() { return name; }
        public CreateAiProvider setName(String value) { this.name = value; return this; }
        public String getApiKeyVar() { return apiKeyVar; }
        public CreateAiProvider setApiKeyVar(String value) { this.apiKeyVar = value; return this; }
        public String getApiKey() { return apiKey; }
        public CreateAiProvider setApiKey(String value) { this.apiKey = value; return this; }
        public String getApiKeyHeader() { return apiKeyHeader; }
        public CreateAiProvider setApiKeyHeader(String value) { this.apiKeyHeader = value; return this; }
        public String getHeartbeatUrl() { return heartbeatUrl; }
        public CreateAiProvider setHeartbeatUrl(String value) { this.heartbeatUrl = value; return this; }
        public HashMap<TaskType,String> getTaskPaths() { return taskPaths; }
        public CreateAiProvider setTaskPaths(HashMap<TaskType,String> value) { this.taskPaths = value; return this; }
        public Integer getConcurrency() { return concurrency; }
        public CreateAiProvider setConcurrency(Integer value) { this.concurrency = value; return this; }
        public Integer getPriority() { return priority; }
        public CreateAiProvider setPriority(Integer value) { this.priority = value; return this; }
        public Boolean isEnabled() { return enabled; }
        public CreateAiProvider setEnabled(Boolean value) { this.enabled = value; return this; }
        public ArrayList<AiProviderModel> getModels() { return models; }
        public CreateAiProvider setModels(ArrayList<AiProviderModel> value) { this.models = value; return this; }
        public ArrayList<String> getSelectedModels() { return selectedModels; }
        public CreateAiProvider setSelectedModels(ArrayList<String> value) { this.selectedModels = value; return this; }
        private static Object responseType = IdResponse.class;
        public Object getResponseType() { return responseType; }
    }

    public static class UpdateAiProvider implements IReturn<IdResponse>, IPatchDb<AiProvider>
    {
        public Integer id = null;
        /**
        * The Type of this API Provider
        */
        public String aiTypeId = null;
        /**
        * The Base URL for the API Provider
        */
        public String apiBaseUrl = null;
        /**
        * The unique name for this API Provider
        */
        public String name = null;
        /**
        * The API Key to use for this Provider
        */
        public String apiKeyVar = null;
        /**
        * The API Key to use for this Provider
        */
        public String apiKey = null;
        /**
        * Send the API Key in the Header instead of Authorization Bearer
        */
        public String apiKeyHeader = null;
        /**
        * The URL to check if the API Provider is still online
        */
        public String heartbeatUrl = null;
        /**
        * Override API Paths for different AI Requests
        */
        public HashMap<TaskType,String> taskPaths = null;
        /**
        * How many requests should be made concurrently
        */
        public Integer concurrency = null;
        /**
        * What priority to give this Provider to use for processing models
        */
        public Integer priority = null;
        /**
        * Whether the Provider is enabled
        */
        public Boolean enabled = null;
        /**
        * The models this API Provider should process
        */
        public ArrayList<AiProviderModel> models = null;
        /**
        * The selected models this API Provider should process
        */
        public ArrayList<String> selectedModels = null;
        
        public Integer getId() { return id; }
        public UpdateAiProvider setId(Integer value) { this.id = value; return this; }
        public String getAiTypeId() { return aiTypeId; }
        public UpdateAiProvider setAiTypeId(String value) { this.aiTypeId = value; return this; }
        public String getApiBaseUrl() { return apiBaseUrl; }
        public UpdateAiProvider setApiBaseUrl(String value) { this.apiBaseUrl = value; return this; }
        public String getName() { return name; }
        public UpdateAiProvider setName(String value) { this.name = value; return this; }
        public String getApiKeyVar() { return apiKeyVar; }
        public UpdateAiProvider setApiKeyVar(String value) { this.apiKeyVar = value; return this; }
        public String getApiKey() { return apiKey; }
        public UpdateAiProvider setApiKey(String value) { this.apiKey = value; return this; }
        public String getApiKeyHeader() { return apiKeyHeader; }
        public UpdateAiProvider setApiKeyHeader(String value) { this.apiKeyHeader = value; return this; }
        public String getHeartbeatUrl() { return heartbeatUrl; }
        public UpdateAiProvider setHeartbeatUrl(String value) { this.heartbeatUrl = value; return this; }
        public HashMap<TaskType,String> getTaskPaths() { return taskPaths; }
        public UpdateAiProvider setTaskPaths(HashMap<TaskType,String> value) { this.taskPaths = value; return this; }
        public Integer getConcurrency() { return concurrency; }
        public UpdateAiProvider setConcurrency(Integer value) { this.concurrency = value; return this; }
        public Integer getPriority() { return priority; }
        public UpdateAiProvider setPriority(Integer value) { this.priority = value; return this; }
        public Boolean isEnabled() { return enabled; }
        public UpdateAiProvider setEnabled(Boolean value) { this.enabled = value; return this; }
        public ArrayList<AiProviderModel> getModels() { return models; }
        public UpdateAiProvider setModels(ArrayList<AiProviderModel> value) { this.models = value; return this; }
        public ArrayList<String> getSelectedModels() { return selectedModels; }
        public UpdateAiProvider setSelectedModels(ArrayList<String> value) { this.selectedModels = value; return this; }
        private static Object responseType = IdResponse.class;
        public Object getResponseType() { return responseType; }
    }

    /**
    * Delete API Provider
    */
    public static class DeleteAiProvider implements IReturnVoid, IDeleteDb<AiProvider>
    {
        public Integer id = null;
        
        public Integer getId() { return id; }
        public DeleteAiProvider setId(Integer value) { this.id = value; return this; }
    }

    public static class QueryPrompts extends QueryData<Prompt> implements IReturn<QueryResponse<Prompt>>
    {
        
        private static Object responseType = new TypeToken<QueryResponse<Prompt>>(){}.getType();
        public Object getResponseType() { return responseType; }
    }

    public static class Reload implements IReturn<EmptyResponse>, IPost
    {
        
        private static Object responseType = EmptyResponse.class;
        public Object getResponseType() { return responseType; }
    }

    public static class ChangeAiProviderStatus implements IReturn<StringResponse>, IPost
    {
        public String provider = null;
        public Boolean online = null;
        
        public String getProvider() { return provider; }
        public ChangeAiProviderStatus setProvider(String value) { this.provider = value; return this; }
        public Boolean isOnline() { return online; }
        public ChangeAiProviderStatus setOnline(Boolean value) { this.online = value; return this; }
        private static Object responseType = StringResponse.class;
        public Object getResponseType() { return responseType; }
    }

    /**
    * Convert text to speech
    */
    @Api(Description="Convert text to speech")
    public static class QueueTextToSpeech implements IReturn<QueueGenerationResponse>, IQueueGeneration
    {
        /**
        * The text to be converted to speech
        */
        @ApiMember(Description="The text to be converted to speech")
        @Required()
        public String text = null;

        /**
        * Optional seed for reproducible results in speech generation
        */
        @ApiMember(Description="Optional seed for reproducible results in speech generation")
        public Integer seed = null;

        /**
        * The AI model to use for speech generation
        */
        @ApiMember(Description="The AI model to use for speech generation")
        public String model = null;

        /**
        * Optional client-provided identifier for the request
        */
        @ApiMember(Description="Optional client-provided identifier for the request")
        public String refId = null;

        /**
        * Optional queue or topic to reply to
        */
        @ApiMember(Description="Optional queue or topic to reply to")
        public String replyTo = null;

        /**
        * Tag to identify the request
        */
        @ApiMember(Description="Tag to identify the request")
        public String tag = null;

        /**
        * Optional state to associate with the request
        */
        @ApiMember(Description="Optional state to associate with the request")
        public String state = null;
        
        public String getText() { return text; }
        public QueueTextToSpeech setText(String value) { this.text = value; return this; }
        public Integer getSeed() { return seed; }
        public QueueTextToSpeech setSeed(Integer value) { this.seed = value; return this; }
        public String getModel() { return model; }
        public QueueTextToSpeech setModel(String value) { this.model = value; return this; }
        public String getRefId() { return refId; }
        public QueueTextToSpeech setRefId(String value) { this.refId = value; return this; }
        public String getReplyTo() { return replyTo; }
        public QueueTextToSpeech setReplyTo(String value) { this.replyTo = value; return this; }
        public String getTag() { return tag; }
        public QueueTextToSpeech setTag(String value) { this.tag = value; return this; }
        public String getState() { return state; }
        public QueueTextToSpeech setState(String value) { this.state = value; return this; }
        private static Object responseType = QueueGenerationResponse.class;
        public Object getResponseType() { return responseType; }
    }

    /**
    * Convert speech to text
    */
    @Api(Description="Convert speech to text")
    public static class QueueSpeechToText implements IReturn<QueueGenerationResponse>, IQueueGeneration
    {
        /**
        * The audio stream containing the speech to be transcribed
        */
        @ApiMember(Description="The audio stream containing the speech to be transcribed")
        @Required()
        public InputStream audio = null;

        /**
        * Optional client-provided identifier for the request
        */
        @ApiMember(Description="Optional client-provided identifier for the request")
        public String refId = null;

        /**
        * Optional queue or topic to reply to
        */
        @ApiMember(Description="Optional queue or topic to reply to")
        public String replyTo = null;

        /**
        * Tag to identify the request
        */
        @ApiMember(Description="Tag to identify the request")
        public String tag = null;

        /**
        * Optional state to associate with the request
        */
        @ApiMember(Description="Optional state to associate with the request")
        public String state = null;
        
        public InputStream getAudio() { return audio; }
        public QueueSpeechToText setAudio(InputStream value) { this.audio = value; return this; }
        public String getRefId() { return refId; }
        public QueueSpeechToText setRefId(String value) { this.refId = value; return this; }
        public String getReplyTo() { return replyTo; }
        public QueueSpeechToText setReplyTo(String value) { this.replyTo = value; return this; }
        public String getTag() { return tag; }
        public QueueSpeechToText setTag(String value) { this.tag = value; return this; }
        public String getState() { return state; }
        public QueueSpeechToText setState(String value) { this.state = value; return this; }
        private static Object responseType = QueueGenerationResponse.class;
        public Object getResponseType() { return responseType; }
    }

    /**
    * Convert text to speech
    */
    @Api(Description="Convert text to speech")
    public static class TextToSpeech implements IReturn<GenerationResponse>, IGeneration
    {
        /**
        * The text to be converted to speech
        */
        @ApiMember(Description="The text to be converted to speech")
        @Validate(Validator="NotEmpty")
        public String input = null;

        /**
        * Optional specific model and voice to use for speech generation
        */
        @ApiMember(Description="Optional specific model and voice to use for speech generation")
        public String model = null;

        /**
        * Optional seed for reproducible results in speech generation
        */
        @ApiMember(Description="Optional seed for reproducible results in speech generation")
        public Integer seed = null;

        /**
        * Optional client-provided identifier for the request
        */
        @ApiMember(Description="Optional client-provided identifier for the request")
        public String refId = null;

        /**
        * Tag to identify the request
        */
        @ApiMember(Description="Tag to identify the request")
        public String tag = null;
        
        public String getInput() { return input; }
        public TextToSpeech setInput(String value) { this.input = value; return this; }
        public String getModel() { return model; }
        public TextToSpeech setModel(String value) { this.model = value; return this; }
        public Integer getSeed() { return seed; }
        public TextToSpeech setSeed(Integer value) { this.seed = value; return this; }
        public String getRefId() { return refId; }
        public TextToSpeech setRefId(String value) { this.refId = value; return this; }
        public String getTag() { return tag; }
        public TextToSpeech setTag(String value) { this.tag = value; return this; }
        private static Object responseType = GenerationResponse.class;
        public Object getResponseType() { return responseType; }
    }

    /**
    * Convert speech to text
    */
    @Api(Description="Convert speech to text")
    public static class SpeechToText implements IReturn<GenerationResponse>, IGeneration
    {
        /**
        * The audio stream containing the speech to be transcribed
        */
        @ApiMember(Description="The audio stream containing the speech to be transcribed")
        @Required()
        public InputStream audio = null;

        /**
        * Optional client-provided identifier for the request
        */
        @ApiMember(Description="Optional client-provided identifier for the request")
        public String refId = null;

        /**
        * Tag to identify the request
        */
        @ApiMember(Description="Tag to identify the request")
        public String tag = null;
        
        public InputStream getAudio() { return audio; }
        public SpeechToText setAudio(InputStream value) { this.audio = value; return this; }
        public String getRefId() { return refId; }
        public SpeechToText setRefId(String value) { this.refId = value; return this; }
        public String getTag() { return tag; }
        public SpeechToText setTag(String value) { this.tag = value; return this; }
        private static Object responseType = GenerationResponse.class;
        public Object getResponseType() { return responseType; }
    }

    /**
    * Scale video
    */
    @Api(Description="Scale video")
    public static class ScaleVideo implements IReturn<MediaTransformResponse>, IMediaTransform
    {
        /**
        * The video file to be scaled
        */
        @ApiMember(Description="The video file to be scaled")
        @Required()
        public InputStream video = null;

        /**
        * Desired width of the scaled video
        */
        @ApiMember(Description="Desired width of the scaled video")
        public Integer width = null;

        /**
        * Desired height of the scaled video
        */
        @ApiMember(Description="Desired height of the scaled video")
        public Integer height = null;

        /**
        * Optional client-provided identifier for the request
        */
        @ApiMember(Description="Optional client-provided identifier for the request")
        public String refId = null;

        /**
        * Tag to identify the request
        */
        @ApiMember(Description="Tag to identify the request")
        public String tag = null;
        
        public InputStream getVideo() { return video; }
        public ScaleVideo setVideo(InputStream value) { this.video = value; return this; }
        public Integer getWidth() { return width; }
        public ScaleVideo setWidth(Integer value) { this.width = value; return this; }
        public Integer getHeight() { return height; }
        public ScaleVideo setHeight(Integer value) { this.height = value; return this; }
        public String getRefId() { return refId; }
        public ScaleVideo setRefId(String value) { this.refId = value; return this; }
        public String getTag() { return tag; }
        public ScaleVideo setTag(String value) { this.tag = value; return this; }
        private static Object responseType = MediaTransformResponse.class;
        public Object getResponseType() { return responseType; }
    }

    /**
    * Watermark video
    */
    @Api(Description="Watermark video")
    public static class WatermarkVideo implements IReturn<MediaTransformResponse>, IMediaTransform
    {
        /**
        * The video file to be watermarked
        */
        @ApiMember(Description="The video file to be watermarked")
        @Required()
        public InputStream video = null;

        /**
        * The image file to use as a watermark
        */
        @ApiMember(Description="The image file to use as a watermark")
        @Required()
        public InputStream watermark = null;

        /**
        * Position of the watermark
        */
        @ApiMember(Description="Position of the watermark")
        public WatermarkPosition position = null;

        /**
        * Optional client-provided identifier for the request
        */
        @ApiMember(Description="Optional client-provided identifier for the request")
        public String refId = null;

        /**
        * Tag to identify the request
        */
        @ApiMember(Description="Tag to identify the request")
        public String tag = null;
        
        public InputStream getVideo() { return video; }
        public WatermarkVideo setVideo(InputStream value) { this.video = value; return this; }
        public InputStream getWatermark() { return watermark; }
        public WatermarkVideo setWatermark(InputStream value) { this.watermark = value; return this; }
        public WatermarkPosition getPosition() { return position; }
        public WatermarkVideo setPosition(WatermarkPosition value) { this.position = value; return this; }
        public String getRefId() { return refId; }
        public WatermarkVideo setRefId(String value) { this.refId = value; return this; }
        public String getTag() { return tag; }
        public WatermarkVideo setTag(String value) { this.tag = value; return this; }
        private static Object responseType = MediaTransformResponse.class;
        public Object getResponseType() { return responseType; }
    }

    /**
    * Convert a video to a different format
    */
    public static class ConvertVideo implements IReturn<MediaTransformResponse>, IMediaTransform
    {
        /**
        * The desired output format for the converted video
        */
        @ApiMember(Description="The desired output format for the converted video")
        @Required()
        public ConvertVideoOutputFormat outputFormat = null;

        @Required()
        public InputStream video = null;

        /**
        * Optional client-provided identifier for the request
        */
        @ApiMember(Description="Optional client-provided identifier for the request")
        public String refId = null;

        /**
        * Tag to identify the request
        */
        @ApiMember(Description="Tag to identify the request")
        public String tag = null;
        
        public ConvertVideoOutputFormat getOutputFormat() { return outputFormat; }
        public ConvertVideo setOutputFormat(ConvertVideoOutputFormat value) { this.outputFormat = value; return this; }
        public InputStream getVideo() { return video; }
        public ConvertVideo setVideo(InputStream value) { this.video = value; return this; }
        public String getRefId() { return refId; }
        public ConvertVideo setRefId(String value) { this.refId = value; return this; }
        public String getTag() { return tag; }
        public ConvertVideo setTag(String value) { this.tag = value; return this; }
        private static Object responseType = MediaTransformResponse.class;
        public Object getResponseType() { return responseType; }
    }

    /**
    * Crop a video to a specified area
    */
    public static class CropVideo implements IReturn<MediaTransformResponse>, IMediaTransform
    {
        /**
        * The X-coordinate of the top-left corner of the crop area
        */
        @ApiMember(Description="The X-coordinate of the top-left corner of the crop area")
        @Validate(Validator="GreaterThan(0)")
        @Required()
        public Integer x = null;

        /**
        * The Y-coordinate of the top-left corner of the crop area
        */
        @ApiMember(Description="The Y-coordinate of the top-left corner of the crop area")
        @Validate(Validator="GreaterThan(0)")
        @Required()
        public Integer y = null;

        /**
        * The width of the crop area
        */
        @ApiMember(Description="The width of the crop area")
        @Validate(Validator="GreaterThan(0)")
        @Required()
        public Integer width = null;

        /**
        * The height of the crop area
        */
        @ApiMember(Description="The height of the crop area")
        @Validate(Validator="GreaterThan(0)")
        @Required()
        public Integer height = null;

        @Required()
        public InputStream video = null;

        /**
        * Optional client-provided identifier for the request
        */
        @ApiMember(Description="Optional client-provided identifier for the request")
        public String refId = null;

        /**
        * Tag to identify the request
        */
        @ApiMember(Description="Tag to identify the request")
        public String tag = null;
        
        public Integer getX() { return x; }
        public CropVideo setX(Integer value) { this.x = value; return this; }
        public Integer getY() { return y; }
        public CropVideo setY(Integer value) { this.y = value; return this; }
        public Integer getWidth() { return width; }
        public CropVideo setWidth(Integer value) { this.width = value; return this; }
        public Integer getHeight() { return height; }
        public CropVideo setHeight(Integer value) { this.height = value; return this; }
        public InputStream getVideo() { return video; }
        public CropVideo setVideo(InputStream value) { this.video = value; return this; }
        public String getRefId() { return refId; }
        public CropVideo setRefId(String value) { this.refId = value; return this; }
        public String getTag() { return tag; }
        public CropVideo setTag(String value) { this.tag = value; return this; }
        private static Object responseType = MediaTransformResponse.class;
        public Object getResponseType() { return responseType; }
    }

    /**
    * Trim a video to a specified duration via start and end times
    */
    public static class TrimVideo implements IReturn<MediaTransformResponse>, IMediaTransform
    {
        /**
        * The start time of the trimmed video (format: MM:SS)
        */
        @ApiMember(Description="The start time of the trimmed video (format: MM:SS)")
        @Required()
        public String startTime = null;

        /**
        * The end time of the trimmed video (format: MM:SS)
        */
        @ApiMember(Description="The end time of the trimmed video (format: MM:SS)")
        public String endTime = null;

        @Required()
        public InputStream video = null;

        /**
        * Optional client-provided identifier for the request
        */
        @ApiMember(Description="Optional client-provided identifier for the request")
        public String refId = null;

        /**
        * Tag to identify the request
        */
        @ApiMember(Description="Tag to identify the request")
        public String tag = null;
        
        public String getStartTime() { return startTime; }
        public TrimVideo setStartTime(String value) { this.startTime = value; return this; }
        public String getEndTime() { return endTime; }
        public TrimVideo setEndTime(String value) { this.endTime = value; return this; }
        public InputStream getVideo() { return video; }
        public TrimVideo setVideo(InputStream value) { this.video = value; return this; }
        public String getRefId() { return refId; }
        public TrimVideo setRefId(String value) { this.refId = value; return this; }
        public String getTag() { return tag; }
        public TrimVideo setTag(String value) { this.tag = value; return this; }
        private static Object responseType = MediaTransformResponse.class;
        public Object getResponseType() { return responseType; }
    }

    /**
    * Scale video
    */
    @Api(Description="Scale video")
    public static class QueueScaleVideo implements IReturn<QueueMediaTransformResponse>, IQueueMediaTransform
    {
        /**
        * The video file to be scaled
        */
        @ApiMember(Description="The video file to be scaled")
        @Required()
        public InputStream video = null;

        /**
        * Desired width of the scaled video
        */
        @ApiMember(Description="Desired width of the scaled video")
        public Integer width = null;

        /**
        * Desired height of the scaled video
        */
        @ApiMember(Description="Desired height of the scaled video")
        public Integer height = null;

        /**
        * Optional client-provided identifier for the request
        */
        @ApiMember(Description="Optional client-provided identifier for the request")
        public String refId = null;

        /**
        * Optional queue or topic to reply to
        */
        @ApiMember(Description="Optional queue or topic to reply to")
        public String replyTo = null;

        /**
        * Tag to identify the request
        */
        @ApiMember(Description="Tag to identify the request")
        public String tag = null;
        
        public InputStream getVideo() { return video; }
        public QueueScaleVideo setVideo(InputStream value) { this.video = value; return this; }
        public Integer getWidth() { return width; }
        public QueueScaleVideo setWidth(Integer value) { this.width = value; return this; }
        public Integer getHeight() { return height; }
        public QueueScaleVideo setHeight(Integer value) { this.height = value; return this; }
        public String getRefId() { return refId; }
        public QueueScaleVideo setRefId(String value) { this.refId = value; return this; }
        public String getReplyTo() { return replyTo; }
        public QueueScaleVideo setReplyTo(String value) { this.replyTo = value; return this; }
        public String getTag() { return tag; }
        public QueueScaleVideo setTag(String value) { this.tag = value; return this; }
        private static Object responseType = QueueMediaTransformResponse.class;
        public Object getResponseType() { return responseType; }
    }

    /**
    * Watermark video
    */
    @Api(Description="Watermark video")
    public static class QueueWatermarkVideo implements IReturn<QueueMediaTransformResponse>, IQueueMediaTransform
    {
        /**
        * The video file to be watermarked
        */
        @ApiMember(Description="The video file to be watermarked")
        @Required()
        public InputStream video = null;

        /**
        * The image file to use as a watermark
        */
        @ApiMember(Description="The image file to use as a watermark")
        @Required()
        public InputStream watermark = null;

        /**
        * Position of the watermark
        */
        @ApiMember(Description="Position of the watermark")
        public WatermarkPosition position = null;

        /**
        * Optional client-provided identifier for the request
        */
        @ApiMember(Description="Optional client-provided identifier for the request")
        public String refId = null;

        /**
        * Optional queue or topic to reply to
        */
        @ApiMember(Description="Optional queue or topic to reply to")
        public String replyTo = null;

        /**
        * Tag to identify the request
        */
        @ApiMember(Description="Tag to identify the request")
        public String tag = null;
        
        public InputStream getVideo() { return video; }
        public QueueWatermarkVideo setVideo(InputStream value) { this.video = value; return this; }
        public InputStream getWatermark() { return watermark; }
        public QueueWatermarkVideo setWatermark(InputStream value) { this.watermark = value; return this; }
        public WatermarkPosition getPosition() { return position; }
        public QueueWatermarkVideo setPosition(WatermarkPosition value) { this.position = value; return this; }
        public String getRefId() { return refId; }
        public QueueWatermarkVideo setRefId(String value) { this.refId = value; return this; }
        public String getReplyTo() { return replyTo; }
        public QueueWatermarkVideo setReplyTo(String value) { this.replyTo = value; return this; }
        public String getTag() { return tag; }
        public QueueWatermarkVideo setTag(String value) { this.tag = value; return this; }
        private static Object responseType = QueueMediaTransformResponse.class;
        public Object getResponseType() { return responseType; }
    }

    /**
    * Convert a video to a different format
    */
    public static class QueueConvertVideo implements IReturn<QueueMediaTransformResponse>, IQueueMediaTransform
    {
        /**
        * The desired output format for the converted video
        */
        @ApiMember(Description="The desired output format for the converted video")
        @Required()
        public ConvertVideoOutputFormat outputFormat = null;

        @Required()
        public InputStream video = null;

        /**
        * Optional client-provided identifier for the request
        */
        @ApiMember(Description="Optional client-provided identifier for the request")
        public String refId = null;

        /**
        * Optional queue or topic to reply to
        */
        @ApiMember(Description="Optional queue or topic to reply to")
        public String replyTo = null;

        /**
        * Tag to identify the request
        */
        @ApiMember(Description="Tag to identify the request")
        public String tag = null;
        
        public ConvertVideoOutputFormat getOutputFormat() { return outputFormat; }
        public QueueConvertVideo setOutputFormat(ConvertVideoOutputFormat value) { this.outputFormat = value; return this; }
        public InputStream getVideo() { return video; }
        public QueueConvertVideo setVideo(InputStream value) { this.video = value; return this; }
        public String getRefId() { return refId; }
        public QueueConvertVideo setRefId(String value) { this.refId = value; return this; }
        public String getReplyTo() { return replyTo; }
        public QueueConvertVideo setReplyTo(String value) { this.replyTo = value; return this; }
        public String getTag() { return tag; }
        public QueueConvertVideo setTag(String value) { this.tag = value; return this; }
        private static Object responseType = QueueMediaTransformResponse.class;
        public Object getResponseType() { return responseType; }
    }

    /**
    * Crop a video to a specified area
    */
    public static class QueueCropVideo implements IReturn<QueueMediaTransformResponse>, IQueueMediaTransform
    {
        /**
        * The X-coordinate of the top-left corner of the crop area
        */
        @ApiMember(Description="The X-coordinate of the top-left corner of the crop area")
        @Validate(Validator="GreaterThan(0)")
        @Required()
        public Integer x = null;

        /**
        * The Y-coordinate of the top-left corner of the crop area
        */
        @ApiMember(Description="The Y-coordinate of the top-left corner of the crop area")
        @Validate(Validator="GreaterThan(0)")
        @Required()
        public Integer y = null;

        /**
        * The width of the crop area
        */
        @ApiMember(Description="The width of the crop area")
        @Validate(Validator="GreaterThan(0)")
        @Required()
        public Integer width = null;

        /**
        * The height of the crop area
        */
        @ApiMember(Description="The height of the crop area")
        @Validate(Validator="GreaterThan(0)")
        @Required()
        public Integer height = null;

        @Required()
        public InputStream video = null;

        /**
        * Optional client-provided identifier for the request
        */
        @ApiMember(Description="Optional client-provided identifier for the request")
        public String refId = null;

        /**
        * Optional queue or topic to reply to
        */
        @ApiMember(Description="Optional queue or topic to reply to")
        public String replyTo = null;

        /**
        * Tag to identify the request
        */
        @ApiMember(Description="Tag to identify the request")
        public String tag = null;
        
        public Integer getX() { return x; }
        public QueueCropVideo setX(Integer value) { this.x = value; return this; }
        public Integer getY() { return y; }
        public QueueCropVideo setY(Integer value) { this.y = value; return this; }
        public Integer getWidth() { return width; }
        public QueueCropVideo setWidth(Integer value) { this.width = value; return this; }
        public Integer getHeight() { return height; }
        public QueueCropVideo setHeight(Integer value) { this.height = value; return this; }
        public InputStream getVideo() { return video; }
        public QueueCropVideo setVideo(InputStream value) { this.video = value; return this; }
        public String getRefId() { return refId; }
        public QueueCropVideo setRefId(String value) { this.refId = value; return this; }
        public String getReplyTo() { return replyTo; }
        public QueueCropVideo setReplyTo(String value) { this.replyTo = value; return this; }
        public String getTag() { return tag; }
        public QueueCropVideo setTag(String value) { this.tag = value; return this; }
        private static Object responseType = QueueMediaTransformResponse.class;
        public Object getResponseType() { return responseType; }
    }

    /**
    * Trim a video to a specified duration via start and end times
    */
    public static class QueueTrimVideo implements IReturn<QueueMediaTransformResponse>, IQueueMediaTransform
    {
        /**
        * The start time of the trimmed video (format: HH:MM:SS)
        */
        @ApiMember(Description="The start time of the trimmed video (format: HH:MM:SS)")
        @Required()
        public String startTime = null;

        /**
        * The end time of the trimmed video (format: HH:MM:SS)
        */
        @ApiMember(Description="The end time of the trimmed video (format: HH:MM:SS)")
        public String endTime = null;

        @Required()
        public InputStream video = null;

        /**
        * Optional client-provided identifier for the request
        */
        @ApiMember(Description="Optional client-provided identifier for the request")
        public String refId = null;

        /**
        * Optional queue or topic to reply to
        */
        @ApiMember(Description="Optional queue or topic to reply to")
        public String replyTo = null;

        /**
        * Tag to identify the request
        */
        @ApiMember(Description="Tag to identify the request")
        public String tag = null;
        
        public String getStartTime() { return startTime; }
        public QueueTrimVideo setStartTime(String value) { this.startTime = value; return this; }
        public String getEndTime() { return endTime; }
        public QueueTrimVideo setEndTime(String value) { this.endTime = value; return this; }
        public InputStream getVideo() { return video; }
        public QueueTrimVideo setVideo(InputStream value) { this.video = value; return this; }
        public String getRefId() { return refId; }
        public QueueTrimVideo setRefId(String value) { this.refId = value; return this; }
        public String getReplyTo() { return replyTo; }
        public QueueTrimVideo setReplyTo(String value) { this.replyTo = value; return this; }
        public String getTag() { return tag; }
        public QueueTrimVideo setTag(String value) { this.tag = value; return this; }
        private static Object responseType = QueueMediaTransformResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route(Path="/artifacts/{**Path}")
    public static class GetArtifact implements IReturn<byte[]>, IGet
    {
        @Validate(Validator="NotEmpty")
        public String path = null;

        public Boolean download = null;
        
        public String getPath() { return path; }
        public GetArtifact setPath(String value) { this.path = value; return this; }
        public Boolean isDownload() { return download; }
        public GetArtifact setDownload(Boolean value) { this.download = value; return this; }
        private static Object responseType = byte[].class;
        public Object getResponseType() { return responseType; }
    }

    @Route(Path="/files/{**Path}")
    public static class DeleteFile implements IReturn<EmptyResponse>, IDelete
    {
        @Validate(Validator="NotEmpty")
        public String path = null;
        
        public String getPath() { return path; }
        public DeleteFile setPath(String value) { this.path = value; return this; }
        private static Object responseType = EmptyResponse.class;
        public Object getResponseType() { return responseType; }
    }

    public static class DeleteFiles implements IReturn<DeleteFilesResponse>, IPost
    {
        public ArrayList<String> paths = null;
        
        public ArrayList<String> getPaths() { return paths; }
        public DeleteFiles setPaths(ArrayList<String> value) { this.paths = value; return this; }
        private static Object responseType = DeleteFilesResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route(Path="/variants/{Variant}/{**Path}")
    public static class GetVariant implements IReturn<byte[]>, IGet
    {
        @Validate(Validator="NotEmpty")
        public String variant = null;

        @Validate(Validator="NotEmpty")
        public String path = null;
        
        public String getVariant() { return variant; }
        public GetVariant setVariant(String value) { this.variant = value; return this; }
        public String getPath() { return path; }
        public GetVariant setPath(String value) { this.path = value; return this; }
        private static Object responseType = byte[].class;
        public Object getResponseType() { return responseType; }
    }

    public static class MigrateArtifact implements IReturn<MigrateArtifactResponse>, IPost
    {
        public String path = null;
        public Date date = null;
        
        public String getPath() { return path; }
        public MigrateArtifact setPath(String value) { this.path = value; return this; }
        public Date getDate() { return date; }
        public MigrateArtifact setDate(Date value) { this.date = value; return this; }
        private static Object responseType = MigrateArtifactResponse.class;
        public Object getResponseType() { return responseType; }
    }

    /**
    * Sign In
    */
    @Route(Path="/auth", Verbs="GET,POST")
    // @Route(Path="/auth/{provider}", Verbs="GET,POST")
    @Api(Description="Sign In")
    @DataContract
    public static class Authenticate implements IReturn<AuthenticateResponse>, IPost
    {
        /**
        * AuthProvider, e.g. credentials
        */
        @DataMember(Order=1)
        public String provider = null;

        @DataMember(Order=2)
        public String userName = null;

        @DataMember(Order=3)
        public String password = null;

        @DataMember(Order=4)
        public Boolean rememberMe = null;

        @DataMember(Order=5)
        public String accessToken = null;

        @DataMember(Order=6)
        public String accessTokenSecret = null;

        @DataMember(Order=7)
        public String returnUrl = null;

        @DataMember(Order=8)
        public String errorView = null;

        @DataMember(Order=9)
        public HashMap<String,String> meta = null;
        
        public String getProvider() { return provider; }
        public Authenticate setProvider(String value) { this.provider = value; return this; }
        public String getUserName() { return userName; }
        public Authenticate setUserName(String value) { this.userName = value; return this; }
        public String getPassword() { return password; }
        public Authenticate setPassword(String value) { this.password = value; return this; }
        public Boolean isRememberMe() { return rememberMe; }
        public Authenticate setRememberMe(Boolean value) { this.rememberMe = value; return this; }
        public String getAccessToken() { return accessToken; }
        public Authenticate setAccessToken(String value) { this.accessToken = value; return this; }
        public String getAccessTokenSecret() { return accessTokenSecret; }
        public Authenticate setAccessTokenSecret(String value) { this.accessTokenSecret = value; return this; }
        public String getReturnUrl() { return returnUrl; }
        public Authenticate setReturnUrl(String value) { this.returnUrl = value; return this; }
        public String getErrorView() { return errorView; }
        public Authenticate setErrorView(String value) { this.errorView = value; return this; }
        public HashMap<String,String> getMeta() { return meta; }
        public Authenticate setMeta(HashMap<String,String> value) { this.meta = value; return this; }
        private static Object responseType = AuthenticateResponse.class;
        public Object getResponseType() { return responseType; }
    }

    public static class QueryMediaTypesData extends QueryData<MediaType> implements IReturn<QueryResponse<MediaType>>
    {
        
        private static Object responseType = new TypeToken<QueryResponse<MediaType>>(){}.getType();
        public Object getResponseType() { return responseType; }
    }

    public static class QueryTextToSpeechVoicesData extends QueryData<TextToSpeechVoice> implements IReturn<QueryResponse<TextToSpeechVoice>>
    {
        
        private static Object responseType = new TypeToken<QueryResponse<TextToSpeechVoice>>(){}.getType();
        public Object getResponseType() { return responseType; }
    }

    public static class QueryAiModelsData extends QueryData<AiModel> implements IReturn<QueryResponse<AiModel>>
    {
        
        private static Object responseType = new TypeToken<QueryResponse<AiModel>>(){}.getType();
        public Object getResponseType() { return responseType; }
    }

    public static class QueryAiTypesData extends QueryData<AiType> implements IReturn<QueryResponse<AiType>>
    {
        
        private static Object responseType = new TypeToken<QueryResponse<AiType>>(){}.getType();
        public Object getResponseType() { return responseType; }
    }

    /**
    * Delete a Generation API Provider
    */
    public static class DeleteMediaProvider implements IReturn<IdResponse>, IDeleteDb<MediaProvider>
    {
        public Integer id = null;
        public String name = null;
        
        public Integer getId() { return id; }
        public DeleteMediaProvider setId(Integer value) { this.id = value; return this; }
        public String getName() { return name; }
        public DeleteMediaProvider setName(String value) { this.name = value; return this; }
        private static Object responseType = IdResponse.class;
        public Object getResponseType() { return responseType; }
    }

    public static class AdminDataResponse
    {
        public ArrayList<PageStats> pageStats = null;
        
        public ArrayList<PageStats> getPageStats() { return pageStats; }
        public AdminDataResponse setPageStats(ArrayList<PageStats> value) { this.pageStats = value; return this; }
    }

    /**
    * Response object for transform requests
    */
    public static class MediaTransformResponse
    {
        /**
        * List of generated outputs
        */
        @ApiMember(Description="List of generated outputs")
        public ArrayList<ArtifactOutput> outputs = null;

        /**
        * List of generated text outputs
        */
        @ApiMember(Description="List of generated text outputs")
        public ArrayList<TextOutput> textOutputs = null;

        /**
        * Detailed response status information
        */
        @ApiMember(Description="Detailed response status information")
        public ResponseStatus responseStatus = null;
        
        public ArrayList<ArtifactOutput> getOutputs() { return outputs; }
        public MediaTransformResponse setOutputs(ArrayList<ArtifactOutput> value) { this.outputs = value; return this; }
        public ArrayList<TextOutput> getTextOutputs() { return textOutputs; }
        public MediaTransformResponse setTextOutputs(ArrayList<TextOutput> value) { this.textOutputs = value; return this; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public MediaTransformResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    /**
    * Base class for queueable transformation requests
    */
    public static class QueueMediaTransformResponse
    {
        /**
        * Unique identifier of the background job
        */
        @ApiMember(Description="Unique identifier of the background job")
        public Long jobId = null;

        /**
        * Client-provided identifier for the request
        */
        @ApiMember(Description="Client-provided identifier for the request")
        public String refId = null;

        /**
        * Current state of the background job
        */
        @ApiMember(Description="Current state of the background job")
        public BackgroundJobState jobState = null;

        /**
        * Current status of the transformation request
        */
        @ApiMember(Description="Current status of the transformation request")
        public String status = null;

        /**
        * Detailed response status information
        */
        @ApiMember(Description="Detailed response status information")
        public ResponseStatus responseStatus = null;

        /**
        * URL to check the status of the request
        */
        @ApiMember(Description="URL to check the status of the request")
        public String statusUrl = null;
        
        public Long getJobId() { return jobId; }
        public QueueMediaTransformResponse setJobId(Long value) { this.jobId = value; return this; }
        public String getRefId() { return refId; }
        public QueueMediaTransformResponse setRefId(String value) { this.refId = value; return this; }
        public BackgroundJobState getJobState() { return jobState; }
        public QueueMediaTransformResponse setJobState(BackgroundJobState value) { this.jobState = value; return this; }
        public String getStatus() { return status; }
        public QueueMediaTransformResponse setStatus(String value) { this.status = value; return this; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public QueueMediaTransformResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
        public String getStatusUrl() { return statusUrl; }
        public QueueMediaTransformResponse setStatusUrl(String value) { this.statusUrl = value; return this; }
    }

    public static class GetSummaryStatsResponse
    {
        public ArrayList<SummaryStats> providerStats = null;
        public ArrayList<SummaryStats> modelStats = null;
        public ArrayList<SummaryStats> monthStats = null;
        
        public ArrayList<SummaryStats> getProviderStats() { return providerStats; }
        public GetSummaryStatsResponse setProviderStats(ArrayList<SummaryStats> value) { this.providerStats = value; return this; }
        public ArrayList<SummaryStats> getModelStats() { return modelStats; }
        public GetSummaryStatsResponse setModelStats(ArrayList<SummaryStats> value) { this.modelStats = value; return this; }
        public ArrayList<SummaryStats> getMonthStats() { return monthStats; }
        public GetSummaryStatsResponse setMonthStats(ArrayList<SummaryStats> value) { this.monthStats = value; return this; }
    }

    @DataContract
    public static class StringsResponse
    {
        @DataMember(Order=1)
        public ArrayList<String> results = null;

        @DataMember(Order=2)
        public HashMap<String,String> meta = null;

        @DataMember(Order=3)
        public ResponseStatus responseStatus = null;
        
        public ArrayList<String> getResults() { return results; }
        public StringsResponse setResults(ArrayList<String> value) { this.results = value; return this; }
        public HashMap<String,String> getMeta() { return meta; }
        public StringsResponse setMeta(HashMap<String,String> value) { this.meta = value; return this; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public StringsResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    public static class GetComfyModelsResponse
    {
        public ArrayList<String> results = null;
        public ResponseStatus responseStatus = null;
        
        public ArrayList<String> getResults() { return results; }
        public GetComfyModelsResponse setResults(ArrayList<String> value) { this.results = value; return this; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public GetComfyModelsResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    public static class GetComfyModelMappingsResponse
    {
        public HashMap<String,String> models = null;
        
        public HashMap<String,String> getModels() { return models; }
        public GetComfyModelMappingsResponse setModels(HashMap<String,String> value) { this.models = value; return this; }
    }

    public static class GetJobStatusResponse
    {
        /**
        * Unique identifier of the background job
        */
        @ApiMember(Description="Unique identifier of the background job")
        public Long jobId = null;

        /**
        * Client-provided identifier for the request
        */
        @ApiMember(Description="Client-provided identifier for the request")
        public String refId = null;

        /**
        * Current state of the background job
        */
        @ApiMember(Description="Current state of the background job")
        public BackgroundJobState jobState = null;

        /**
        * Current status of the generation request
        */
        @ApiMember(Description="Current status of the generation request")
        public String status = null;

        /**
        * List of generated outputs
        */
        @ApiMember(Description="List of generated outputs")
        public ArrayList<ArtifactOutput> outputs = null;

        /**
        * List of generated text outputs
        */
        @ApiMember(Description="List of generated text outputs")
        public ArrayList<TextOutput> textOutputs = null;

        /**
        * Detailed response status information
        */
        @ApiMember(Description="Detailed response status information")
        public ResponseStatus responseStatus = null;
        
        public Long getJobId() { return jobId; }
        public GetJobStatusResponse setJobId(Long value) { this.jobId = value; return this; }
        public String getRefId() { return refId; }
        public GetJobStatusResponse setRefId(String value) { this.refId = value; return this; }
        public BackgroundJobState getJobState() { return jobState; }
        public GetJobStatusResponse setJobState(BackgroundJobState value) { this.jobState = value; return this; }
        public String getStatus() { return status; }
        public GetJobStatusResponse setStatus(String value) { this.status = value; return this; }
        public ArrayList<ArtifactOutput> getOutputs() { return outputs; }
        public GetJobStatusResponse setOutputs(ArrayList<ArtifactOutput> value) { this.outputs = value; return this; }
        public ArrayList<TextOutput> getTextOutputs() { return textOutputs; }
        public GetJobStatusResponse setTextOutputs(ArrayList<TextOutput> value) { this.textOutputs = value; return this; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public GetJobStatusResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    /**
    * Response object for generation requests
    */
    public static class GenerationResponse
    {
        /**
        * List of generated outputs
        */
        @ApiMember(Description="List of generated outputs")
        public ArrayList<ArtifactOutput> outputs = null;

        /**
        * List of generated text outputs
        */
        @ApiMember(Description="List of generated text outputs")
        public ArrayList<TextOutput> textOutputs = null;

        /**
        * Detailed response status information
        */
        @ApiMember(Description="Detailed response status information")
        public ResponseStatus responseStatus = null;
        
        public ArrayList<ArtifactOutput> getOutputs() { return outputs; }
        public GenerationResponse setOutputs(ArrayList<ArtifactOutput> value) { this.outputs = value; return this; }
        public ArrayList<TextOutput> getTextOutputs() { return textOutputs; }
        public GenerationResponse setTextOutputs(ArrayList<TextOutput> value) { this.textOutputs = value; return this; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public GenerationResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    public static class QueueGenerationResponse
    {
        /**
        * Unique identifier of the background job
        */
        @ApiMember(Description="Unique identifier of the background job")
        public Long jobId = null;

        /**
        * Client-provided identifier for the request
        */
        @ApiMember(Description="Client-provided identifier for the request")
        public String refId = null;

        /**
        * Current state of the background job
        */
        @ApiMember(Description="Current state of the background job")
        public BackgroundJobState jobState = null;

        /**
        * Current status of the generation request
        */
        @ApiMember(Description="Current status of the generation request")
        public String status = null;

        /**
        * Detailed response status information
        */
        @ApiMember(Description="Detailed response status information")
        public ResponseStatus responseStatus = null;

        /**
        * URL to check the status of the generation request
        */
        @ApiMember(Description="URL to check the status of the generation request")
        public String statusUrl = null;
        
        public Long getJobId() { return jobId; }
        public QueueGenerationResponse setJobId(Long value) { this.jobId = value; return this; }
        public String getRefId() { return refId; }
        public QueueGenerationResponse setRefId(String value) { this.refId = value; return this; }
        public BackgroundJobState getJobState() { return jobState; }
        public QueueGenerationResponse setJobState(BackgroundJobState value) { this.jobState = value; return this; }
        public String getStatus() { return status; }
        public QueueGenerationResponse setStatus(String value) { this.status = value; return this; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public QueueGenerationResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
        public String getStatusUrl() { return statusUrl; }
        public QueueGenerationResponse setStatusUrl(String value) { this.statusUrl = value; return this; }
    }

    @DataContract
    public static class QueryResponse<T>
    {
        @DataMember(Order=1)
        public Integer offset = null;

        @DataMember(Order=2)
        public Integer total = null;

        @DataMember(Order=3)
        public ArrayList<MediaType> results = null;

        @DataMember(Order=4)
        public HashMap<String,String> meta = null;

        @DataMember(Order=5)
        public ResponseStatus responseStatus = null;
        
        public Integer getOffset() { return offset; }
        public QueryResponse<T> setOffset(Integer value) { this.offset = value; return this; }
        public Integer getTotal() { return total; }
        public QueryResponse<T> setTotal(Integer value) { this.total = value; return this; }
        public ArrayList<MediaType> getResults() { return results; }
        public QueryResponse<T> setResults(ArrayList<MediaType> value) { this.results = value; return this; }
        public HashMap<String,String> getMeta() { return meta; }
        public QueryResponse<T> setMeta(HashMap<String,String> value) { this.meta = value; return this; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public QueryResponse<T> setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    public static class CreateGenerationResponse
    {
        public Long id = null;
        public String refId = null;
        
        public Long getId() { return id; }
        public CreateGenerationResponse setId(Long value) { this.id = value; return this; }
        public String getRefId() { return refId; }
        public CreateGenerationResponse setRefId(String value) { this.refId = value; return this; }
    }

    public static class GetGenerationResponse
    {
        public GenerationArgs request = null;
        public GenerationResult result = null;
        public ArrayList<AiProviderFileOutput> outputs = null;
        public ArrayList<AiProviderTextOutput> textOutputs = null;
        
        public GenerationArgs getRequest() { return request; }
        public GetGenerationResponse setRequest(GenerationArgs value) { this.request = value; return this; }
        public GenerationResult getResult() { return result; }
        public GetGenerationResponse setResult(GenerationResult value) { this.result = value; return this; }
        public ArrayList<AiProviderFileOutput> getOutputs() { return outputs; }
        public GetGenerationResponse setOutputs(ArrayList<AiProviderFileOutput> value) { this.outputs = value; return this; }
        public ArrayList<AiProviderTextOutput> getTextOutputs() { return textOutputs; }
        public GetGenerationResponse setTextOutputs(ArrayList<AiProviderTextOutput> value) { this.textOutputs = value; return this; }
    }

    @DataContract
    public static class IdResponse
    {
        @DataMember(Order=1)
        public String id = null;

        @DataMember(Order=2)
        public ResponseStatus responseStatus = null;
        
        public String getId() { return id; }
        public IdResponse setId(String value) { this.id = value; return this; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public IdResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    public static class CreateTransformResponse
    {
        public Long id = null;
        public String refId = null;
        
        public Long getId() { return id; }
        public CreateTransformResponse setId(Long value) { this.id = value; return this; }
        public String getRefId() { return refId; }
        public CreateTransformResponse setRefId(String value) { this.refId = value; return this; }
    }

    public static class HelloResponse
    {
        public String result = null;
        
        public String getResult() { return result; }
        public HelloResponse setResult(String value) { this.result = value; return this; }
    }

    public static class GetOllamaModelsResponse
    {
        public ArrayList<OllamaModel> results = null;
        public ResponseStatus responseStatus = null;
        
        public ArrayList<OllamaModel> getResults() { return results; }
        public GetOllamaModelsResponse setResults(ArrayList<OllamaModel> value) { this.results = value; return this; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public GetOllamaModelsResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    public static class GetWorkerStatsResponse
    {
        public ArrayList<WorkerStats> results = null;
        public HashMap<String,Integer> queueCounts = null;
        public ResponseStatus responseStatus = null;
        
        public ArrayList<WorkerStats> getResults() { return results; }
        public GetWorkerStatsResponse setResults(ArrayList<WorkerStats> value) { this.results = value; return this; }
        public HashMap<String,Integer> getQueueCounts() { return queueCounts; }
        public GetWorkerStatsResponse setQueueCounts(HashMap<String,Integer> value) { this.queueCounts = value; return this; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public GetWorkerStatsResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    @DataContract
    public static class EmptyResponse
    {
        @DataMember(Order=1)
        public ResponseStatus responseStatus = null;
        
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public EmptyResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    @DataContract
    public static class OpenAiChatResponse
    {
        /**
        * A unique identifier for the chat completion.
        */
        @DataMember(Name="id")
        @SerializedName("id")
        public String id = null;

        /**
        * A list of chat completion choices. Can be more than one if n is greater than 1.
        */
        @DataMember(Name="choices")
        @SerializedName("choices")
        public ArrayList<Choice> choices = null;

        /**
        * The Unix timestamp (in seconds) of when the chat completion was created.
        */
        @DataMember(Name="created")
        @SerializedName("created")
        public Long created = null;

        /**
        * The model used for the chat completion.
        */
        @DataMember(Name="model")
        @SerializedName("model")
        public String model = null;

        /**
        * This fingerprint represents the backend configuration that the model runs with.
        */
        @DataMember(Name="system_fingerprint")
        @SerializedName("system_fingerprint")
        public String systemFingerprint = null;

        /**
        * The object type, which is always chat.completion.
        */
        @DataMember(Name="object")
        @SerializedName("object")
        public String object = null;

        /**
        * Usage statistics for the completion request.
        */
        @DataMember(Name="usage")
        @SerializedName("usage")
        public OpenAiUsage usage = null;

        @DataMember(Name="responseStatus")
        @SerializedName("responseStatus")
        public ResponseStatus responseStatus = null;
        
        public String getId() { return id; }
        public OpenAiChatResponse setId(String value) { this.id = value; return this; }
        public ArrayList<Choice> getChoices() { return choices; }
        public OpenAiChatResponse setChoices(ArrayList<Choice> value) { this.choices = value; return this; }
        public Long getCreated() { return created; }
        public OpenAiChatResponse setCreated(Long value) { this.created = value; return this; }
        public String getModel() { return model; }
        public OpenAiChatResponse setModel(String value) { this.model = value; return this; }
        public String getSystemFingerprint() { return systemFingerprint; }
        public OpenAiChatResponse setSystemFingerprint(String value) { this.systemFingerprint = value; return this; }
        public String getObject() { return object; }
        public OpenAiChatResponse setObject(String value) { this.object = value; return this; }
        public OpenAiUsage getUsage() { return usage; }
        public OpenAiChatResponse setUsage(OpenAiUsage value) { this.usage = value; return this; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public OpenAiChatResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    public static class QueueOpenAiChatResponse
    {
        public Long id = null;
        public String refId = null;
        public String statusUrl = null;
        public ResponseStatus responseStatus = null;
        
        public Long getId() { return id; }
        public QueueOpenAiChatResponse setId(Long value) { this.id = value; return this; }
        public String getRefId() { return refId; }
        public QueueOpenAiChatResponse setRefId(String value) { this.refId = value; return this; }
        public String getStatusUrl() { return statusUrl; }
        public QueueOpenAiChatResponse setStatusUrl(String value) { this.statusUrl = value; return this; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public QueueOpenAiChatResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    public static class GetOpenAiChatResponse
    {
        public BackgroundJobBase result = null;
        public ResponseStatus responseStatus = null;
        
        public BackgroundJobBase getResult() { return result; }
        public GetOpenAiChatResponse setResult(BackgroundJobBase value) { this.result = value; return this; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public GetOpenAiChatResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    public static class GetOpenAiChatStatusResponse
    {
        /**
        * Unique identifier of the background job
        */
        @ApiMember(Description="Unique identifier of the background job")
        public Long jobId = null;

        /**
        * Client-provided identifier for the request
        */
        @ApiMember(Description="Client-provided identifier for the request")
        public String refId = null;

        /**
        * Current state of the background job
        */
        @ApiMember(Description="Current state of the background job")
        public BackgroundJobState jobState = null;

        /**
        * Current status of the generation request
        */
        @ApiMember(Description="Current status of the generation request")
        public String status = null;

        /**
        * Detailed response status information
        */
        @ApiMember(Description="Detailed response status information")
        public ResponseStatus responseStatus = null;

        /**
        * Chat response
        */
        @ApiMember(Description="Chat response")
        public OpenAiChatResponse chatResponse = null;
        
        public Long getJobId() { return jobId; }
        public GetOpenAiChatStatusResponse setJobId(Long value) { this.jobId = value; return this; }
        public String getRefId() { return refId; }
        public GetOpenAiChatStatusResponse setRefId(String value) { this.refId = value; return this; }
        public BackgroundJobState getJobState() { return jobState; }
        public GetOpenAiChatStatusResponse setJobState(BackgroundJobState value) { this.jobState = value; return this; }
        public String getStatus() { return status; }
        public GetOpenAiChatStatusResponse setStatus(String value) { this.status = value; return this; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public GetOpenAiChatStatusResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
        public OpenAiChatResponse getChatResponse() { return chatResponse; }
        public GetOpenAiChatStatusResponse setChatResponse(OpenAiChatResponse value) { this.chatResponse = value; return this; }
    }

    public static class GetActiveProvidersResponse
    {
        public ArrayList<AiProvider> results = null;
        public ResponseStatus responseStatus = null;
        
        public ArrayList<AiProvider> getResults() { return results; }
        public GetActiveProvidersResponse setResults(ArrayList<AiProvider> value) { this.results = value; return this; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public GetActiveProvidersResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    public static class CreateApiKeyResponse
    {
        public Integer id = null;
        public String key = null;
        public String name = null;
        public String userId = null;
        public String userName = null;
        public String visibleKey = null;
        public Date createdDate = null;
        public Date expiryDate = null;
        public Date cancelledDate = null;
        public String notes = null;
        
        public Integer getId() { return id; }
        public CreateApiKeyResponse setId(Integer value) { this.id = value; return this; }
        public String getKey() { return key; }
        public CreateApiKeyResponse setKey(String value) { this.key = value; return this; }
        public String getName() { return name; }
        public CreateApiKeyResponse setName(String value) { this.name = value; return this; }
        public String getUserId() { return userId; }
        public CreateApiKeyResponse setUserId(String value) { this.userId = value; return this; }
        public String getUserName() { return userName; }
        public CreateApiKeyResponse setUserName(String value) { this.userName = value; return this; }
        public String getVisibleKey() { return visibleKey; }
        public CreateApiKeyResponse setVisibleKey(String value) { this.visibleKey = value; return this; }
        public Date getCreatedDate() { return createdDate; }
        public CreateApiKeyResponse setCreatedDate(Date value) { this.createdDate = value; return this; }
        public Date getExpiryDate() { return expiryDate; }
        public CreateApiKeyResponse setExpiryDate(Date value) { this.expiryDate = value; return this; }
        public Date getCancelledDate() { return cancelledDate; }
        public CreateApiKeyResponse setCancelledDate(Date value) { this.cancelledDate = value; return this; }
        public String getNotes() { return notes; }
        public CreateApiKeyResponse setNotes(String value) { this.notes = value; return this; }
    }

    @DataContract
    public static class StringResponse
    {
        @DataMember(Order=1)
        public String result = null;

        @DataMember(Order=2)
        public HashMap<String,String> meta = null;

        @DataMember(Order=3)
        public ResponseStatus responseStatus = null;
        
        public String getResult() { return result; }
        public StringResponse setResult(String value) { this.result = value; return this; }
        public HashMap<String,String> getMeta() { return meta; }
        public StringResponse setMeta(HashMap<String,String> value) { this.meta = value; return this; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public StringResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    public static class DeleteFilesResponse
    {
        public ArrayList<String> deleted = null;
        public ArrayList<String> missing = null;
        public ArrayList<String> failed = null;
        public ResponseStatus responseStatus = null;
        
        public ArrayList<String> getDeleted() { return deleted; }
        public DeleteFilesResponse setDeleted(ArrayList<String> value) { this.deleted = value; return this; }
        public ArrayList<String> getMissing() { return missing; }
        public DeleteFilesResponse setMissing(ArrayList<String> value) { this.missing = value; return this; }
        public ArrayList<String> getFailed() { return failed; }
        public DeleteFilesResponse setFailed(ArrayList<String> value) { this.failed = value; return this; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public DeleteFilesResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    public static class MigrateArtifactResponse
    {
        public String filePath = null;
        public ResponseStatus responseStatus = null;
        
        public String getFilePath() { return filePath; }
        public MigrateArtifactResponse setFilePath(String value) { this.filePath = value; return this; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public MigrateArtifactResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    @DataContract
    public static class AuthenticateResponse implements IHasSessionId, IHasBearerToken
    {
        @DataMember(Order=1)
        public String userId = null;

        @DataMember(Order=2)
        public String sessionId = null;

        @DataMember(Order=3)
        public String userName = null;

        @DataMember(Order=4)
        public String displayName = null;

        @DataMember(Order=5)
        public String referrerUrl = null;

        @DataMember(Order=6)
        public String bearerToken = null;

        @DataMember(Order=7)
        public String refreshToken = null;

        @DataMember(Order=8)
        public Date refreshTokenExpiry = null;

        @DataMember(Order=9)
        public String profileUrl = null;

        @DataMember(Order=10)
        public ArrayList<String> roles = null;

        @DataMember(Order=11)
        public ArrayList<String> permissions = null;

        @DataMember(Order=12)
        public String authProvider = null;

        @DataMember(Order=13)
        public ResponseStatus responseStatus = null;

        @DataMember(Order=14)
        public HashMap<String,String> meta = null;
        
        public String getUserId() { return userId; }
        public AuthenticateResponse setUserId(String value) { this.userId = value; return this; }
        public String getSessionId() { return sessionId; }
        public AuthenticateResponse setSessionId(String value) { this.sessionId = value; return this; }
        public String getUserName() { return userName; }
        public AuthenticateResponse setUserName(String value) { this.userName = value; return this; }
        public String getDisplayName() { return displayName; }
        public AuthenticateResponse setDisplayName(String value) { this.displayName = value; return this; }
        public String getReferrerUrl() { return referrerUrl; }
        public AuthenticateResponse setReferrerUrl(String value) { this.referrerUrl = value; return this; }
        public String getBearerToken() { return bearerToken; }
        public AuthenticateResponse setBearerToken(String value) { this.bearerToken = value; return this; }
        public String getRefreshToken() { return refreshToken; }
        public AuthenticateResponse setRefreshToken(String value) { this.refreshToken = value; return this; }
        public Date getRefreshTokenExpiry() { return refreshTokenExpiry; }
        public AuthenticateResponse setRefreshTokenExpiry(Date value) { this.refreshTokenExpiry = value; return this; }
        public String getProfileUrl() { return profileUrl; }
        public AuthenticateResponse setProfileUrl(String value) { this.profileUrl = value; return this; }
        public ArrayList<String> getRoles() { return roles; }
        public AuthenticateResponse setRoles(ArrayList<String> value) { this.roles = value; return this; }
        public ArrayList<String> getPermissions() { return permissions; }
        public AuthenticateResponse setPermissions(ArrayList<String> value) { this.permissions = value; return this; }
        public String getAuthProvider() { return authProvider; }
        public AuthenticateResponse setAuthProvider(String value) { this.authProvider = value; return this; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public AuthenticateResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
        public HashMap<String,String> getMeta() { return meta; }
        public AuthenticateResponse setMeta(HashMap<String,String> value) { this.meta = value; return this; }
    }

    @DataContract
    public static enum AudioFormat
    {
        MP3,
        Wav,
        Flac,
        Ogg;
    }

    public static interface IMediaTransform
    {
        public String refId = null;
        public String tag = null;
    }

    public static interface IQueueMediaTransform
    {
        public String refId = null;
        public String tag = null;
        public String replyTo = null;
    }

    public static interface IGeneration
    {
        public String refId = null;
        public String tag = null;
    }

    /**
    * Base class for queue generation requests
    */
    public static interface IQueueGeneration
    {
        public String refId = null;
        public String replyTo = null;
        public String tag = null;
        public String state = null;
    }

    @DataContract
    public static enum ImageOutputFormat
    {
        Jpg,
        Png,
        Gif,
        Bmp,
        Tiff,
        Webp;
    }

    public static enum WatermarkPosition
    {
        TopLeft,
        TopRight,
        BottomLeft,
        BottomRight,
        Center;
    }

    public static class QueryDb<T> extends QueryBase
    {
        
    }

    public static class MediaType
    {
        public String id = null;
        public String apiBaseUrl = null;
        public String apiKeyHeader = null;
        public String website = null;
        public String icon = null;
        public HashMap<String,String> apiModels = null;
        public AiServiceProvider provider = null;
        
        public String getId() { return id; }
        public MediaType setId(String value) { this.id = value; return this; }
        public String getApiBaseUrl() { return apiBaseUrl; }
        public MediaType setApiBaseUrl(String value) { this.apiBaseUrl = value; return this; }
        public String getApiKeyHeader() { return apiKeyHeader; }
        public MediaType setApiKeyHeader(String value) { this.apiKeyHeader = value; return this; }
        public String getWebsite() { return website; }
        public MediaType setWebsite(String value) { this.website = value; return this; }
        public String getIcon() { return icon; }
        public MediaType setIcon(String value) { this.icon = value; return this; }
        public HashMap<String,String> getApiModels() { return apiModels; }
        public MediaType setApiModels(HashMap<String,String> value) { this.apiModels = value; return this; }
        public AiServiceProvider getProvider() { return provider; }
        public MediaType setProvider(AiServiceProvider value) { this.provider = value; return this; }
    }

    public static class MediaProvider
    {
        public Integer id = null;
        public String name = null;
        public String apiKeyVar = null;
        public String apiUrlVar = null;
        public String apiKey = null;
        public String apiKeyHeader = null;
        public String apiBaseUrl = null;
        public String heartbeatUrl = null;
        public Integer concurrency = null;
        public Integer priority = null;
        public Boolean enabled = null;
        public Date offlineDate = null;
        public Date createdDate = null;
        public String mediaTypeId = null;
        @Ignore()
        public MediaType mediaType = null;

        public ArrayList<String> models = null;
        
        public Integer getId() { return id; }
        public MediaProvider setId(Integer value) { this.id = value; return this; }
        public String getName() { return name; }
        public MediaProvider setName(String value) { this.name = value; return this; }
        public String getApiKeyVar() { return apiKeyVar; }
        public MediaProvider setApiKeyVar(String value) { this.apiKeyVar = value; return this; }
        public String getApiUrlVar() { return apiUrlVar; }
        public MediaProvider setApiUrlVar(String value) { this.apiUrlVar = value; return this; }
        public String getApiKey() { return apiKey; }
        public MediaProvider setApiKey(String value) { this.apiKey = value; return this; }
        public String getApiKeyHeader() { return apiKeyHeader; }
        public MediaProvider setApiKeyHeader(String value) { this.apiKeyHeader = value; return this; }
        public String getApiBaseUrl() { return apiBaseUrl; }
        public MediaProvider setApiBaseUrl(String value) { this.apiBaseUrl = value; return this; }
        public String getHeartbeatUrl() { return heartbeatUrl; }
        public MediaProvider setHeartbeatUrl(String value) { this.heartbeatUrl = value; return this; }
        public Integer getConcurrency() { return concurrency; }
        public MediaProvider setConcurrency(Integer value) { this.concurrency = value; return this; }
        public Integer getPriority() { return priority; }
        public MediaProvider setPriority(Integer value) { this.priority = value; return this; }
        public Boolean isEnabled() { return enabled; }
        public MediaProvider setEnabled(Boolean value) { this.enabled = value; return this; }
        public Date getOfflineDate() { return offlineDate; }
        public MediaProvider setOfflineDate(Date value) { this.offlineDate = value; return this; }
        public Date getCreatedDate() { return createdDate; }
        public MediaProvider setCreatedDate(Date value) { this.createdDate = value; return this; }
        public String getMediaTypeId() { return mediaTypeId; }
        public MediaProvider setMediaTypeId(String value) { this.mediaTypeId = value; return this; }
        public MediaType getMediaType() { return mediaType; }
        public MediaProvider setMediaType(MediaType value) { this.mediaType = value; return this; }
        public ArrayList<String> getModels() { return models; }
        public MediaProvider setModels(ArrayList<String> value) { this.models = value; return this; }
    }

    public static class TextToSpeechVoice
    {
        public String id = null;
        public String model = null;
        
        public String getId() { return id; }
        public TextToSpeechVoice setId(String value) { this.id = value; return this; }
        public String getModel() { return model; }
        public TextToSpeechVoice setModel(String value) { this.model = value; return this; }
    }

    public static class GenerationArgs
    {
        public String model = null;
        public Integer steps = null;
        public Integer batchSize = null;
        public Integer seed = null;
        public String positivePrompt = null;
        public String negativePrompt = null;
        public InputStream imageInput = null;
        public InputStream maskInput = null;
        public InputStream audioInput = null;
        public ComfySampler sampler = null;
        public String scheduler = null;
        public Double cfgScale = null;
        public Double denoise = null;
        public String upscaleModel = null;
        public Integer width = null;
        public Integer height = null;
        public AiTaskType taskType = null;
        public String clip = null;
        public Double sampleLength = null;
        public ComfyMaskSource maskChannel = null;
        public String aspectRatio = null;
        public Double quality = null;
        public String voice = null;
        public String language = null;
        
        public String getModel() { return model; }
        public GenerationArgs setModel(String value) { this.model = value; return this; }
        public Integer getSteps() { return steps; }
        public GenerationArgs setSteps(Integer value) { this.steps = value; return this; }
        public Integer getBatchSize() { return batchSize; }
        public GenerationArgs setBatchSize(Integer value) { this.batchSize = value; return this; }
        public Integer getSeed() { return seed; }
        public GenerationArgs setSeed(Integer value) { this.seed = value; return this; }
        public String getPositivePrompt() { return positivePrompt; }
        public GenerationArgs setPositivePrompt(String value) { this.positivePrompt = value; return this; }
        public String getNegativePrompt() { return negativePrompt; }
        public GenerationArgs setNegativePrompt(String value) { this.negativePrompt = value; return this; }
        public InputStream getImageInput() { return imageInput; }
        public GenerationArgs setImageInput(InputStream value) { this.imageInput = value; return this; }
        public InputStream getMaskInput() { return maskInput; }
        public GenerationArgs setMaskInput(InputStream value) { this.maskInput = value; return this; }
        public InputStream getAudioInput() { return audioInput; }
        public GenerationArgs setAudioInput(InputStream value) { this.audioInput = value; return this; }
        public ComfySampler getSampler() { return sampler; }
        public GenerationArgs setSampler(ComfySampler value) { this.sampler = value; return this; }
        public String getScheduler() { return scheduler; }
        public GenerationArgs setScheduler(String value) { this.scheduler = value; return this; }
        public Double getCfgScale() { return cfgScale; }
        public GenerationArgs setCfgScale(Double value) { this.cfgScale = value; return this; }
        public Double getDenoise() { return denoise; }
        public GenerationArgs setDenoise(Double value) { this.denoise = value; return this; }
        public String getUpscaleModel() { return upscaleModel; }
        public GenerationArgs setUpscaleModel(String value) { this.upscaleModel = value; return this; }
        public Integer getWidth() { return width; }
        public GenerationArgs setWidth(Integer value) { this.width = value; return this; }
        public Integer getHeight() { return height; }
        public GenerationArgs setHeight(Integer value) { this.height = value; return this; }
        public AiTaskType getTaskType() { return taskType; }
        public GenerationArgs setTaskType(AiTaskType value) { this.taskType = value; return this; }
        public String getClip() { return clip; }
        public GenerationArgs setClip(String value) { this.clip = value; return this; }
        public Double getSampleLength() { return sampleLength; }
        public GenerationArgs setSampleLength(Double value) { this.sampleLength = value; return this; }
        public ComfyMaskSource getMaskChannel() { return maskChannel; }
        public GenerationArgs setMaskChannel(ComfyMaskSource value) { this.maskChannel = value; return this; }
        public String getAspectRatio() { return aspectRatio; }
        public GenerationArgs setAspectRatio(String value) { this.aspectRatio = value; return this; }
        public Double getQuality() { return quality; }
        public GenerationArgs setQuality(Double value) { this.quality = value; return this; }
        public String getVoice() { return voice; }
        public GenerationArgs setVoice(String value) { this.voice = value; return this; }
        public String getLanguage() { return language; }
        public GenerationArgs setLanguage(String value) { this.language = value; return this; }
    }

    public static class MediaModel
    {
        public String id = null;
        public HashMap<String,String> apiModels = null;
        public String url = null;
        public Double quality = null;
        public String aspectRatio = null;
        public Double cfgScale = null;
        public String scheduler = null;
        public ComfySampler sampler = null;
        public Integer width = null;
        public Integer height = null;
        public Integer steps = null;
        public String negativePrompt = null;
        public ModelType modelType = null;
        
        public String getId() { return id; }
        public MediaModel setId(String value) { this.id = value; return this; }
        public HashMap<String,String> getApiModels() { return apiModels; }
        public MediaModel setApiModels(HashMap<String,String> value) { this.apiModels = value; return this; }
        public String getUrl() { return url; }
        public MediaModel setUrl(String value) { this.url = value; return this; }
        public Double getQuality() { return quality; }
        public MediaModel setQuality(Double value) { this.quality = value; return this; }
        public String getAspectRatio() { return aspectRatio; }
        public MediaModel setAspectRatio(String value) { this.aspectRatio = value; return this; }
        public Double getCfgScale() { return cfgScale; }
        public MediaModel setCfgScale(Double value) { this.cfgScale = value; return this; }
        public String getScheduler() { return scheduler; }
        public MediaModel setScheduler(String value) { this.scheduler = value; return this; }
        public ComfySampler getSampler() { return sampler; }
        public MediaModel setSampler(ComfySampler value) { this.sampler = value; return this; }
        public Integer getWidth() { return width; }
        public MediaModel setWidth(Integer value) { this.width = value; return this; }
        public Integer getHeight() { return height; }
        public MediaModel setHeight(Integer value) { this.height = value; return this; }
        public Integer getSteps() { return steps; }
        public MediaModel setSteps(Integer value) { this.steps = value; return this; }
        public String getNegativePrompt() { return negativePrompt; }
        public MediaModel setNegativePrompt(String value) { this.negativePrompt = value; return this; }
        public ModelType getModelType() { return modelType; }
        public MediaModel setModelType(ModelType value) { this.modelType = value; return this; }
    }

    public static class MediaTransformArgs
    {
        public MediaTransformTaskType taskType = null;
        public InputStream videoInput = null;
        public InputStream audioInput = null;
        public InputStream imageInput = null;
        public InputStream watermarkInput = null;
        public String videoFileName = null;
        public String audioFileName = null;
        public String imageFileName = null;
        public String watermarkFileName = null;
        public MediaOutputFormat outputFormat = null;
        public ImageOutputFormat imageOutputFormat = null;
        public Integer scaleWidth = null;
        public Integer scaleHeight = null;
        public Integer cropX = null;
        public Integer cropY = null;
        public Integer cropWidth = null;
        public Integer cropHeight = null;
        public Float cutStart = null;
        public Float cutEnd = null;
        public InputStream watermarkFile = null;
        public String watermarkPosition = null;
        public String watermarkScale = null;
        public String audioCodec = null;
        public String videoCodec = null;
        public String audioBitrate = null;
        public Integer audioSampleRate = null;
        
        public MediaTransformTaskType getTaskType() { return taskType; }
        public MediaTransformArgs setTaskType(MediaTransformTaskType value) { this.taskType = value; return this; }
        public InputStream getVideoInput() { return videoInput; }
        public MediaTransformArgs setVideoInput(InputStream value) { this.videoInput = value; return this; }
        public InputStream getAudioInput() { return audioInput; }
        public MediaTransformArgs setAudioInput(InputStream value) { this.audioInput = value; return this; }
        public InputStream getImageInput() { return imageInput; }
        public MediaTransformArgs setImageInput(InputStream value) { this.imageInput = value; return this; }
        public InputStream getWatermarkInput() { return watermarkInput; }
        public MediaTransformArgs setWatermarkInput(InputStream value) { this.watermarkInput = value; return this; }
        public String getVideoFileName() { return videoFileName; }
        public MediaTransformArgs setVideoFileName(String value) { this.videoFileName = value; return this; }
        public String getAudioFileName() { return audioFileName; }
        public MediaTransformArgs setAudioFileName(String value) { this.audioFileName = value; return this; }
        public String getImageFileName() { return imageFileName; }
        public MediaTransformArgs setImageFileName(String value) { this.imageFileName = value; return this; }
        public String getWatermarkFileName() { return watermarkFileName; }
        public MediaTransformArgs setWatermarkFileName(String value) { this.watermarkFileName = value; return this; }
        public MediaOutputFormat getOutputFormat() { return outputFormat; }
        public MediaTransformArgs setOutputFormat(MediaOutputFormat value) { this.outputFormat = value; return this; }
        public ImageOutputFormat getImageOutputFormat() { return imageOutputFormat; }
        public MediaTransformArgs setImageOutputFormat(ImageOutputFormat value) { this.imageOutputFormat = value; return this; }
        public Integer getScaleWidth() { return scaleWidth; }
        public MediaTransformArgs setScaleWidth(Integer value) { this.scaleWidth = value; return this; }
        public Integer getScaleHeight() { return scaleHeight; }
        public MediaTransformArgs setScaleHeight(Integer value) { this.scaleHeight = value; return this; }
        public Integer getCropX() { return cropX; }
        public MediaTransformArgs setCropX(Integer value) { this.cropX = value; return this; }
        public Integer getCropY() { return cropY; }
        public MediaTransformArgs setCropY(Integer value) { this.cropY = value; return this; }
        public Integer getCropWidth() { return cropWidth; }
        public MediaTransformArgs setCropWidth(Integer value) { this.cropWidth = value; return this; }
        public Integer getCropHeight() { return cropHeight; }
        public MediaTransformArgs setCropHeight(Integer value) { this.cropHeight = value; return this; }
        public Float getCutStart() { return cutStart; }
        public MediaTransformArgs setCutStart(Float value) { this.cutStart = value; return this; }
        public Float getCutEnd() { return cutEnd; }
        public MediaTransformArgs setCutEnd(Float value) { this.cutEnd = value; return this; }
        public InputStream getWatermarkFile() { return watermarkFile; }
        public MediaTransformArgs setWatermarkFile(InputStream value) { this.watermarkFile = value; return this; }
        public String getWatermarkPosition() { return watermarkPosition; }
        public MediaTransformArgs setWatermarkPosition(String value) { this.watermarkPosition = value; return this; }
        public String getWatermarkScale() { return watermarkScale; }
        public MediaTransformArgs setWatermarkScale(String value) { this.watermarkScale = value; return this; }
        public String getAudioCodec() { return audioCodec; }
        public MediaTransformArgs setAudioCodec(String value) { this.audioCodec = value; return this; }
        public String getVideoCodec() { return videoCodec; }
        public MediaTransformArgs setVideoCodec(String value) { this.videoCodec = value; return this; }
        public String getAudioBitrate() { return audioBitrate; }
        public MediaTransformArgs setAudioBitrate(String value) { this.audioBitrate = value; return this; }
        public Integer getAudioSampleRate() { return audioSampleRate; }
        public MediaTransformArgs setAudioSampleRate(Integer value) { this.audioSampleRate = value; return this; }
    }

    public static class AiModel
    {
        public String id = null;
        public ArrayList<String> tags = null;
        public String latest = null;
        public String website = null;
        public String description = null;
        public String icon = null;
        
        public String getId() { return id; }
        public AiModel setId(String value) { this.id = value; return this; }
        public ArrayList<String> getTags() { return tags; }
        public AiModel setTags(ArrayList<String> value) { this.tags = value; return this; }
        public String getLatest() { return latest; }
        public AiModel setLatest(String value) { this.latest = value; return this; }
        public String getWebsite() { return website; }
        public AiModel setWebsite(String value) { this.website = value; return this; }
        public String getDescription() { return description; }
        public AiModel setDescription(String value) { this.description = value; return this; }
        public String getIcon() { return icon; }
        public AiModel setIcon(String value) { this.icon = value; return this; }
    }

    public static class AiType
    {
        public String id = null;
        public AiProviderType provider = null;
        public String website = null;
        public String apiBaseUrl = null;
        public String heartbeatUrl = null;
        public String icon = null;
        public HashMap<String,String> apiModels = null;
        
        public String getId() { return id; }
        public AiType setId(String value) { this.id = value; return this; }
        public AiProviderType getProvider() { return provider; }
        public AiType setProvider(AiProviderType value) { this.provider = value; return this; }
        public String getWebsite() { return website; }
        public AiType setWebsite(String value) { this.website = value; return this; }
        public String getApiBaseUrl() { return apiBaseUrl; }
        public AiType setApiBaseUrl(String value) { this.apiBaseUrl = value; return this; }
        public String getHeartbeatUrl() { return heartbeatUrl; }
        public AiType setHeartbeatUrl(String value) { this.heartbeatUrl = value; return this; }
        public String getIcon() { return icon; }
        public AiType setIcon(String value) { this.icon = value; return this; }
        public HashMap<String,String> getApiModels() { return apiModels; }
        public AiType setApiModels(HashMap<String,String> value) { this.apiModels = value; return this; }
    }

    public static class AiProvider
    {
        public Integer id = null;
        public String name = null;
        public String apiBaseUrl = null;
        public String apiKeyVar = null;
        public String apiKey = null;
        public String apiKeyHeader = null;
        public String heartbeatUrl = null;
        public Integer concurrency = null;
        public Integer priority = null;
        public Boolean enabled = null;
        public Date offlineDate = null;
        public Date createdDate = null;
        public ArrayList<AiProviderModel> models = null;
        public String aiTypeId = null;
        @Ignore()
        public AiType aiType = null;

        @Ignore()
        public ArrayList<String> selectedModels = null;
        
        public Integer getId() { return id; }
        public AiProvider setId(Integer value) { this.id = value; return this; }
        public String getName() { return name; }
        public AiProvider setName(String value) { this.name = value; return this; }
        public String getApiBaseUrl() { return apiBaseUrl; }
        public AiProvider setApiBaseUrl(String value) { this.apiBaseUrl = value; return this; }
        public String getApiKeyVar() { return apiKeyVar; }
        public AiProvider setApiKeyVar(String value) { this.apiKeyVar = value; return this; }
        public String getApiKey() { return apiKey; }
        public AiProvider setApiKey(String value) { this.apiKey = value; return this; }
        public String getApiKeyHeader() { return apiKeyHeader; }
        public AiProvider setApiKeyHeader(String value) { this.apiKeyHeader = value; return this; }
        public String getHeartbeatUrl() { return heartbeatUrl; }
        public AiProvider setHeartbeatUrl(String value) { this.heartbeatUrl = value; return this; }
        public Integer getConcurrency() { return concurrency; }
        public AiProvider setConcurrency(Integer value) { this.concurrency = value; return this; }
        public Integer getPriority() { return priority; }
        public AiProvider setPriority(Integer value) { this.priority = value; return this; }
        public Boolean isEnabled() { return enabled; }
        public AiProvider setEnabled(Boolean value) { this.enabled = value; return this; }
        public Date getOfflineDate() { return offlineDate; }
        public AiProvider setOfflineDate(Date value) { this.offlineDate = value; return this; }
        public Date getCreatedDate() { return createdDate; }
        public AiProvider setCreatedDate(Date value) { this.createdDate = value; return this; }
        public ArrayList<AiProviderModel> getModels() { return models; }
        public AiProvider setModels(ArrayList<AiProviderModel> value) { this.models = value; return this; }
        public String getAiTypeId() { return aiTypeId; }
        public AiProvider setAiTypeId(String value) { this.aiTypeId = value; return this; }
        public AiType getAiType() { return aiType; }
        public AiProvider setAiType(AiType value) { this.aiType = value; return this; }
        public ArrayList<String> getSelectedModels() { return selectedModels; }
        public AiProvider setSelectedModels(ArrayList<String> value) { this.selectedModels = value; return this; }
    }

    /**
    * A list of messages comprising the conversation so far.
    */
    @DataContract
    public static class OpenAiMessage
    {
        /**
        * The contents of the message.
        */
        @DataMember(Name="content")
        @SerializedName("content")
        public String content = null;

        /**
        * The role of the author of this message. Valid values are `system`, `user`, `assistant` and `tool`.
        */
        @DataMember(Name="role")
        @SerializedName("role")
        public String role = null;

        /**
        * An optional name for the participant. Provides the model information to differentiate between participants of the same role.
        */
        @DataMember(Name="name")
        @SerializedName("name")
        public String name = null;

        /**
        * The tool calls generated by the model, such as function calls.
        */
        @DataMember(Name="tool_calls")
        @SerializedName("tool_calls")
        public ArrayList<ToolCall> toolCalls = null;

        /**
        * Tool call that this message is responding to.
        */
        @DataMember(Name="tool_call_id")
        @SerializedName("tool_call_id")
        public String toolCallId = null;
        
        public String getContent() { return content; }
        public OpenAiMessage setContent(String value) { this.content = value; return this; }
        public String getRole() { return role; }
        public OpenAiMessage setRole(String value) { this.role = value; return this; }
        public String getName() { return name; }
        public OpenAiMessage setName(String value) { this.name = value; return this; }
        public ArrayList<ToolCall> getToolCalls() { return toolCalls; }
        public OpenAiMessage setToolCalls(ArrayList<ToolCall> value) { this.toolCalls = value; return this; }
        public String getToolCallId() { return toolCallId; }
        public OpenAiMessage setToolCallId(String value) { this.toolCallId = value; return this; }
    }

    @DataContract
    public static class OpenAiResponseFormat
    {
        /**
        * An object specifying the format that the model must output. Compatible with GPT-4 Turbo and all GPT-3.5 Turbo models newer than gpt-3.5-turbo-1106.
        */
        @DataMember(Name="response_format")
        @SerializedName("response_format")
        public ResponseFormat type = null;
        
        public ResponseFormat getType() { return type; }
        public OpenAiResponseFormat setType(ResponseFormat value) { this.type = value; return this; }
    }

    @DataContract
    public static class OpenAiTools
    {
        /**
        * The type of the tool. Currently, only function is supported.
        */
        @DataMember(Name="type")
        @SerializedName("type")
        public OpenAiToolType type = null;
        
        public OpenAiToolType getType() { return type; }
        public OpenAiTools setType(OpenAiToolType value) { this.type = value; return this; }
    }

    /**
    * Given a list of messages comprising a conversation, the model will return a response.
    */
    @DataContract
    public static class OpenAiChat
    {
        /**
        * A list of messages comprising the conversation so far.
        */
        @DataMember(Name="messages")
        @SerializedName("messages")
        public ArrayList<OpenAiMessage> messages = null;

        /**
        * ID of the model to use. See the model endpoint compatibility table for details on which models work with the Chat API
        */
        @DataMember(Name="model")
        @SerializedName("model")
        public String model = null;

        /**
        * Number between `-2.0` and `2.0`. Positive values penalize new tokens based on their existing frequency in the text so far, decreasing the model's likelihood to repeat the same line verbatim.
        */
        @DataMember(Name="frequency_penalty")
        @SerializedName("frequency_penalty")
        public Double frequencyPenalty = null;

        /**
        * Modify the likelihood of specified tokens appearing in the completion.
        */
        @DataMember(Name="logit_bias")
        @SerializedName("logit_bias")
        public HashMap<Integer,Integer> logitBias = null;

        /**
        * Whether to return log probabilities of the output tokens or not. If true, returns the log probabilities of each output token returned in the content of message.
        */
        @DataMember(Name="logprobs")
        @SerializedName("logprobs")
        public Boolean logProbs = null;

        /**
        * An integer between 0 and 20 specifying the number of most likely tokens to return at each token position, each with an associated log probability. logprobs must be set to true if this parameter is used.
        */
        @DataMember(Name="top_logprobs")
        @SerializedName("top_logprobs")
        public Integer topLogProbs = null;

        /**
        * The maximum number of tokens that can be generated in the chat completion.
        */
        @DataMember(Name="max_tokens")
        @SerializedName("max_tokens")
        public Integer maxTokens = null;

        /**
        * How many chat completion choices to generate for each input message. Note that you will be charged based on the number of generated tokens across all of the choices. Keep `n` as `1` to minimize costs.
        */
        @DataMember(Name="n")
        @SerializedName("n")
        public Integer n = null;

        /**
        * Number between -2.0 and 2.0. Positive values penalize new tokens based on whether they appear in the text so far, increasing the model's likelihood to talk about new topics.
        */
        @DataMember(Name="presence_penalty")
        @SerializedName("presence_penalty")
        public Double presencePenalty = null;

        /**
        * An object specifying the format that the model must output. Compatible with GPT-4 Turbo and all GPT-3.5 Turbo models newer than `gpt-3.5-turbo-1106`. Setting Type to ResponseFormat.JsonObject enables JSON mode, which guarantees the message the model generates is valid JSON.
        */
        @DataMember(Name="response_format")
        @SerializedName("response_format")
        public OpenAiResponseFormat responseFormat = null;

        /**
        * This feature is in Beta. If specified, our system will make a best effort to sample deterministically, such that repeated requests with the same seed and parameters should return the same result. Determinism is not guaranteed, and you should refer to the system_fingerprint response parameter to monitor changes in the backend.
        */
        @DataMember(Name="seed")
        @SerializedName("seed")
        public Integer seed = null;

        /**
        * Up to 4 sequences where the API will stop generating further tokens.
        */
        @DataMember(Name="stop")
        @SerializedName("stop")
        public ArrayList<String> stop = null;

        /**
        * If set, partial message deltas will be sent, like in ChatGPT. Tokens will be sent as data-only server-sent events as they become available, with the stream terminated by a `data: [DONE]` message.
        */
        @DataMember(Name="stream")
        @SerializedName("stream")
        public Boolean stream = null;

        /**
        * What sampling temperature to use, between 0 and 2. Higher values like 0.8 will make the output more random, while lower values like 0.2 will make it more focused and deterministic.
        */
        @DataMember(Name="temperature")
        @SerializedName("temperature")
        public Double temperature = null;

        /**
        * An alternative to sampling with temperature, called nucleus sampling, where the model considers the results of the tokens with top_p probability mass. So 0.1 means only the tokens comprising the top 10% probability mass are considered.
        */
        @DataMember(Name="top_p")
        @SerializedName("top_p")
        public Double topP = null;

        /**
        * A list of tools the model may call. Currently, only functions are supported as a tool. Use this to provide a list of functions the model may generate JSON inputs for. A max of 128 functions are supported.
        */
        @DataMember(Name="tools")
        @SerializedName("tools")
        public ArrayList<OpenAiTools> tools = null;

        /**
        * A unique identifier representing your end-user, which can help OpenAI to monitor and detect abuse.
        */
        @DataMember(Name="user")
        @SerializedName("user")
        public String user = null;
        
        public ArrayList<OpenAiMessage> getMessages() { return messages; }
        public OpenAiChat setMessages(ArrayList<OpenAiMessage> value) { this.messages = value; return this; }
        public String getModel() { return model; }
        public OpenAiChat setModel(String value) { this.model = value; return this; }
        public Double getFrequencyPenalty() { return frequencyPenalty; }
        public OpenAiChat setFrequencyPenalty(Double value) { this.frequencyPenalty = value; return this; }
        public HashMap<Integer,Integer> getLogitBias() { return logitBias; }
        public OpenAiChat setLogitBias(HashMap<Integer,Integer> value) { this.logitBias = value; return this; }
        public Boolean isLogProbs() { return logProbs; }
        public OpenAiChat setLogProbs(Boolean value) { this.logProbs = value; return this; }
        public Integer getTopLogProbs() { return topLogProbs; }
        public OpenAiChat setTopLogProbs(Integer value) { this.topLogProbs = value; return this; }
        public Integer getMaxTokens() { return maxTokens; }
        public OpenAiChat setMaxTokens(Integer value) { this.maxTokens = value; return this; }
        public Integer getN() { return n; }
        public OpenAiChat setN(Integer value) { this.n = value; return this; }
        public Double getPresencePenalty() { return presencePenalty; }
        public OpenAiChat setPresencePenalty(Double value) { this.presencePenalty = value; return this; }
        public OpenAiResponseFormat getResponseFormat() { return responseFormat; }
        public OpenAiChat setResponseFormat(OpenAiResponseFormat value) { this.responseFormat = value; return this; }
        public Integer getSeed() { return seed; }
        public OpenAiChat setSeed(Integer value) { this.seed = value; return this; }
        public ArrayList<String> getStop() { return stop; }
        public OpenAiChat setStop(ArrayList<String> value) { this.stop = value; return this; }
        public Boolean isStream() { return stream; }
        public OpenAiChat setStream(Boolean value) { this.stream = value; return this; }
        public Double getTemperature() { return temperature; }
        public OpenAiChat setTemperature(Double value) { this.temperature = value; return this; }
        public Double getTopP() { return topP; }
        public OpenAiChat setTopP(Double value) { this.topP = value; return this; }
        public ArrayList<OpenAiTools> getTools() { return tools; }
        public OpenAiChat setTools(ArrayList<OpenAiTools> value) { this.tools = value; return this; }
        public String getUser() { return user; }
        public OpenAiChat setUser(String value) { this.user = value; return this; }
    }

    public static enum TaskType
    {
        OpenAiChat(1),
        Comfy(2);

        private final int value;
        TaskType(final int intValue) { value = intValue; }
        public int getValue() { return value; }
    }

    public static class AiProviderModel
    {
        public String model = null;
        public String apiModel = null;
        
        public String getModel() { return model; }
        public AiProviderModel setModel(String value) { this.model = value; return this; }
        public String getApiModel() { return apiModel; }
        public AiProviderModel setApiModel(String value) { this.apiModel = value; return this; }
    }

    public static class QueryData<T> extends QueryBase
    {
        
    }

    public static class Prompt
    {
        public String id = null;
        public String name = null;
        public String value = null;
        
        public String getId() { return id; }
        public Prompt setId(String value) { this.id = value; return this; }
        public String getName() { return name; }
        public Prompt setName(String value) { this.name = value; return this; }
        public String getValue() { return value; }
        public Prompt setValue(String value) { this.value = value; return this; }
    }

    @DataContract
    public static enum ConvertVideoOutputFormat
    {
        MP4,
        Avi,
        Mov;
    }

    public static class PageStats
    {
        public String label = null;
        public Integer total = null;
        
        public String getLabel() { return label; }
        public PageStats setLabel(String value) { this.label = value; return this; }
        public Integer getTotal() { return total; }
        public PageStats setTotal(Integer value) { this.total = value; return this; }
    }

    /**
    * Output object for generated artifacts
    */
    public static class ArtifactOutput
    {
        /**
        * URL to access the generated image
        */
        @ApiMember(Description="URL to access the generated image")
        public String url = null;

        /**
        * Filename of the generated image
        */
        @ApiMember(Description="Filename of the generated image")
        public String fileName = null;

        /**
        * Provider used for image generation
        */
        @ApiMember(Description="Provider used for image generation")
        public String provider = null;
        
        public String getUrl() { return url; }
        public ArtifactOutput setUrl(String value) { this.url = value; return this; }
        public String getFileName() { return fileName; }
        public ArtifactOutput setFileName(String value) { this.fileName = value; return this; }
        public String getProvider() { return provider; }
        public ArtifactOutput setProvider(String value) { this.provider = value; return this; }
    }

    /**
    * Output object for generated text
    */
    public static class TextOutput
    {
        /**
        * The generated text
        */
        @ApiMember(Description="The generated text")
        public String text = null;
        
        public String getText() { return text; }
        public TextOutput setText(String value) { this.text = value; return this; }
    }

    public static enum BackgroundJobState
    {
        Queued,
        Started,
        Executed,
        Completed,
        Failed,
        Cancelled;
    }

    public static class SummaryStats
    {
        public String name = null;
        public Integer total = null;
        public Integer totalPromptTokens = null;
        public Integer totalCompletionTokens = null;
        public Double totalMinutes = null;
        public Double tokensPerSecond = null;
        
        public String getName() { return name; }
        public SummaryStats setName(String value) { this.name = value; return this; }
        public Integer getTotal() { return total; }
        public SummaryStats setTotal(Integer value) { this.total = value; return this; }
        public Integer getTotalPromptTokens() { return totalPromptTokens; }
        public SummaryStats setTotalPromptTokens(Integer value) { this.totalPromptTokens = value; return this; }
        public Integer getTotalCompletionTokens() { return totalCompletionTokens; }
        public SummaryStats setTotalCompletionTokens(Integer value) { this.totalCompletionTokens = value; return this; }
        public Double getTotalMinutes() { return totalMinutes; }
        public SummaryStats setTotalMinutes(Double value) { this.totalMinutes = value; return this; }
        public Double getTokensPerSecond() { return tokensPerSecond; }
        public SummaryStats setTokensPerSecond(Double value) { this.tokensPerSecond = value; return this; }
    }

    public static class GenerationResult
    {
        public ArrayList<AiProviderTextOutput> textOutputs = null;
        public ArrayList<AiProviderFileOutput> outputs = null;
        public String error = null;
        
        public ArrayList<AiProviderTextOutput> getTextOutputs() { return textOutputs; }
        public GenerationResult setTextOutputs(ArrayList<AiProviderTextOutput> value) { this.textOutputs = value; return this; }
        public ArrayList<AiProviderFileOutput> getOutputs() { return outputs; }
        public GenerationResult setOutputs(ArrayList<AiProviderFileOutput> value) { this.outputs = value; return this; }
        public String getError() { return error; }
        public GenerationResult setError(String value) { this.error = value; return this; }
    }

    public static class AiProviderFileOutput
    {
        public String fileName = null;
        public String url = null;
        
        public String getFileName() { return fileName; }
        public AiProviderFileOutput setFileName(String value) { this.fileName = value; return this; }
        public String getUrl() { return url; }
        public AiProviderFileOutput setUrl(String value) { this.url = value; return this; }
    }

    public static class AiProviderTextOutput
    {
        public String text = null;
        
        public String getText() { return text; }
        public AiProviderTextOutput setText(String value) { this.text = value; return this; }
    }

    @DataContract
    public static class OllamaModel
    {
        @DataMember(Name="name")
        @SerializedName("name")
        public String name = null;

        @DataMember(Name="model")
        @SerializedName("model")
        public String model = null;

        @DataMember(Name="modified_at")
        @SerializedName("modified_at")
        public Date modifiedAt = null;

        @DataMember(Name="size")
        @SerializedName("size")
        public Long size = null;

        @DataMember(Name="digest")
        @SerializedName("digest")
        public String digest = null;

        @DataMember(Name="details")
        @SerializedName("details")
        public OllamaModelDetails details = null;
        
        public String getName() { return name; }
        public OllamaModel setName(String value) { this.name = value; return this; }
        public String getModel() { return model; }
        public OllamaModel setModel(String value) { this.model = value; return this; }
        public Date getModifiedAt() { return modifiedAt; }
        public OllamaModel setModifiedAt(Date value) { this.modifiedAt = value; return this; }
        public Long getSize() { return size; }
        public OllamaModel setSize(Long value) { this.size = value; return this; }
        public String getDigest() { return digest; }
        public OllamaModel setDigest(String value) { this.digest = value; return this; }
        public OllamaModelDetails getDetails() { return details; }
        public OllamaModel setDetails(OllamaModelDetails value) { this.details = value; return this; }
    }

    public static class WorkerStats
    {
        public String name = null;
        public Long queued = null;
        public Long received = null;
        public Long completed = null;
        public Long retries = null;
        public Long failed = null;
        public Long runningJob = null;
        public TimeSpan runningTime = null;
        
        public String getName() { return name; }
        public WorkerStats setName(String value) { this.name = value; return this; }
        public Long getQueued() { return queued; }
        public WorkerStats setQueued(Long value) { this.queued = value; return this; }
        public Long getReceived() { return received; }
        public WorkerStats setReceived(Long value) { this.received = value; return this; }
        public Long getCompleted() { return completed; }
        public WorkerStats setCompleted(Long value) { this.completed = value; return this; }
        public Long getRetries() { return retries; }
        public WorkerStats setRetries(Long value) { this.retries = value; return this; }
        public Long getFailed() { return failed; }
        public WorkerStats setFailed(Long value) { this.failed = value; return this; }
        public Long getRunningJob() { return runningJob; }
        public WorkerStats setRunningJob(Long value) { this.runningJob = value; return this; }
        public TimeSpan getRunningTime() { return runningTime; }
        public WorkerStats setRunningTime(TimeSpan value) { this.runningTime = value; return this; }
    }

    public static class Choice
    {
        /**
        * The reason the model stopped generating tokens. This will be stop if the model hit a natural stop point or a provided stop sequence, length if the maximum number of tokens specified in the request was reached, content_filter if content was omitted due to a flag from our content filters, tool_calls if the model called a tool
        */
        @DataMember(Name="finish_reason")
        @SerializedName("finish_reason")
        public String finishReason = null;

        /**
        * The index of the choice in the list of choices.
        */
        @DataMember(Name="index")
        @SerializedName("index")
        public Integer index = null;

        /**
        * A chat completion message generated by the model.
        */
        @DataMember(Name="message")
        @SerializedName("message")
        public ChoiceMessage message = null;
        
        public String getFinishReason() { return finishReason; }
        public Choice setFinishReason(String value) { this.finishReason = value; return this; }
        public Integer getIndex() { return index; }
        public Choice setIndex(Integer value) { this.index = value; return this; }
        public ChoiceMessage getMessage() { return message; }
        public Choice setMessage(ChoiceMessage value) { this.message = value; return this; }
    }

    /**
    * Usage statistics for the completion request.
    */
    @DataContract
    public static class OpenAiUsage
    {
        /**
        * Number of tokens in the generated completion.
        */
        @DataMember(Name="completion_tokens")
        @SerializedName("completion_tokens")
        public Integer completionTokens = null;

        /**
        * Number of tokens in the prompt.
        */
        @DataMember(Name="prompt_tokens")
        @SerializedName("prompt_tokens")
        public Integer promptTokens = null;

        /**
        * Total number of tokens used in the request (prompt + completion).
        */
        @DataMember(Name="total_tokens")
        @SerializedName("total_tokens")
        public Integer totalTokens = null;
        
        public Integer getCompletionTokens() { return completionTokens; }
        public OpenAiUsage setCompletionTokens(Integer value) { this.completionTokens = value; return this; }
        public Integer getPromptTokens() { return promptTokens; }
        public OpenAiUsage setPromptTokens(Integer value) { this.promptTokens = value; return this; }
        public Integer getTotalTokens() { return totalTokens; }
        public OpenAiUsage setTotalTokens(Integer value) { this.totalTokens = value; return this; }
    }

    public static class BackgroundJobBase
    {
        public Long id = null;
        public Long parentId = null;
        public String refId = null;
        public String worker = null;
        public String tag = null;
        public String batchId = null;
        public String callback = null;
        public Long dependsOn = null;
        public Date runAfter = null;
        public Date createdDate = null;
        public String createdBy = null;
        public String requestId = null;
        public String requestType = null;
        public String command = null;
        public String request = null;
        public String requestBody = null;
        public String userId = null;
        public String response = null;
        public String responseBody = null;
        public BackgroundJobState state = null;
        public Date startedDate = null;
        public Date completedDate = null;
        public Date notifiedDate = null;
        public Integer retryLimit = null;
        public Integer attempts = null;
        public Integer durationMs = null;
        public Integer timeoutSecs = null;
        public Double progress = null;
        public String status = null;
        public String logs = null;
        public Date lastActivityDate = null;
        public String replyTo = null;
        public String errorCode = null;
        public ResponseStatus error = null;
        public HashMap<String,String> args = null;
        public HashMap<String,String> meta = null;
        
        public Long getId() { return id; }
        public BackgroundJobBase setId(Long value) { this.id = value; return this; }
        public Long getParentId() { return parentId; }
        public BackgroundJobBase setParentId(Long value) { this.parentId = value; return this; }
        public String getRefId() { return refId; }
        public BackgroundJobBase setRefId(String value) { this.refId = value; return this; }
        public String getWorker() { return worker; }
        public BackgroundJobBase setWorker(String value) { this.worker = value; return this; }
        public String getTag() { return tag; }
        public BackgroundJobBase setTag(String value) { this.tag = value; return this; }
        public String getBatchId() { return batchId; }
        public BackgroundJobBase setBatchId(String value) { this.batchId = value; return this; }
        public String getCallback() { return callback; }
        public BackgroundJobBase setCallback(String value) { this.callback = value; return this; }
        public Long getDependsOn() { return dependsOn; }
        public BackgroundJobBase setDependsOn(Long value) { this.dependsOn = value; return this; }
        public Date getRunAfter() { return runAfter; }
        public BackgroundJobBase setRunAfter(Date value) { this.runAfter = value; return this; }
        public Date getCreatedDate() { return createdDate; }
        public BackgroundJobBase setCreatedDate(Date value) { this.createdDate = value; return this; }
        public String getCreatedBy() { return createdBy; }
        public BackgroundJobBase setCreatedBy(String value) { this.createdBy = value; return this; }
        public String getRequestId() { return requestId; }
        public BackgroundJobBase setRequestId(String value) { this.requestId = value; return this; }
        public String getRequestType() { return requestType; }
        public BackgroundJobBase setRequestType(String value) { this.requestType = value; return this; }
        public String getCommand() { return command; }
        public BackgroundJobBase setCommand(String value) { this.command = value; return this; }
        public String getRequest() { return request; }
        public BackgroundJobBase setRequest(String value) { this.request = value; return this; }
        public String getRequestBody() { return requestBody; }
        public BackgroundJobBase setRequestBody(String value) { this.requestBody = value; return this; }
        public String getUserId() { return userId; }
        public BackgroundJobBase setUserId(String value) { this.userId = value; return this; }
        public String getResponse() { return response; }
        public BackgroundJobBase setResponse(String value) { this.response = value; return this; }
        public String getResponseBody() { return responseBody; }
        public BackgroundJobBase setResponseBody(String value) { this.responseBody = value; return this; }
        public BackgroundJobState getState() { return state; }
        public BackgroundJobBase setState(BackgroundJobState value) { this.state = value; return this; }
        public Date getStartedDate() { return startedDate; }
        public BackgroundJobBase setStartedDate(Date value) { this.startedDate = value; return this; }
        public Date getCompletedDate() { return completedDate; }
        public BackgroundJobBase setCompletedDate(Date value) { this.completedDate = value; return this; }
        public Date getNotifiedDate() { return notifiedDate; }
        public BackgroundJobBase setNotifiedDate(Date value) { this.notifiedDate = value; return this; }
        public Integer getRetryLimit() { return retryLimit; }
        public BackgroundJobBase setRetryLimit(Integer value) { this.retryLimit = value; return this; }
        public Integer getAttempts() { return attempts; }
        public BackgroundJobBase setAttempts(Integer value) { this.attempts = value; return this; }
        public Integer getDurationMs() { return durationMs; }
        public BackgroundJobBase setDurationMs(Integer value) { this.durationMs = value; return this; }
        public Integer getTimeoutSecs() { return timeoutSecs; }
        public BackgroundJobBase setTimeoutSecs(Integer value) { this.timeoutSecs = value; return this; }
        public Double getProgress() { return progress; }
        public BackgroundJobBase setProgress(Double value) { this.progress = value; return this; }
        public String getStatus() { return status; }
        public BackgroundJobBase setStatus(String value) { this.status = value; return this; }
        public String getLogs() { return logs; }
        public BackgroundJobBase setLogs(String value) { this.logs = value; return this; }
        public Date getLastActivityDate() { return lastActivityDate; }
        public BackgroundJobBase setLastActivityDate(Date value) { this.lastActivityDate = value; return this; }
        public String getReplyTo() { return replyTo; }
        public BackgroundJobBase setReplyTo(String value) { this.replyTo = value; return this; }
        public String getErrorCode() { return errorCode; }
        public BackgroundJobBase setErrorCode(String value) { this.errorCode = value; return this; }
        public ResponseStatus getError() { return error; }
        public BackgroundJobBase setError(ResponseStatus value) { this.error = value; return this; }
        public HashMap<String,String> getArgs() { return args; }
        public BackgroundJobBase setArgs(HashMap<String,String> value) { this.args = value; return this; }
        public HashMap<String,String> getMeta() { return meta; }
        public BackgroundJobBase setMeta(HashMap<String,String> value) { this.meta = value; return this; }
    }

    @DataContract
    public static class QueryBase
    {
        @DataMember(Order=1)
        public Integer skip = null;

        @DataMember(Order=2)
        public Integer take = null;

        @DataMember(Order=3)
        public String orderBy = null;

        @DataMember(Order=4)
        public String orderByDesc = null;

        @DataMember(Order=5)
        public String include = null;

        @DataMember(Order=6)
        public String fields = null;

        @DataMember(Order=7)
        public HashMap<String,String> meta = null;
        
        public Integer getSkip() { return skip; }
        public QueryBase setSkip(Integer value) { this.skip = value; return this; }
        public Integer getTake() { return take; }
        public QueryBase setTake(Integer value) { this.take = value; return this; }
        public String getOrderBy() { return orderBy; }
        public QueryBase setOrderBy(String value) { this.orderBy = value; return this; }
        public String getOrderByDesc() { return orderByDesc; }
        public QueryBase setOrderByDesc(String value) { this.orderByDesc = value; return this; }
        public String getInclude() { return include; }
        public QueryBase setInclude(String value) { this.include = value; return this; }
        public String getFields() { return fields; }
        public QueryBase setFields(String value) { this.fields = value; return this; }
        public HashMap<String,String> getMeta() { return meta; }
        public QueryBase setMeta(HashMap<String,String> value) { this.meta = value; return this; }
    }

    public static enum AiServiceProvider
    {
        Replicate,
        Comfy,
        OpenAi;
    }

    public static enum ComfySampler
    {
        Euler,
        EulerCfgPp,
        EulerAncestral,
        EulerAncestralCfgPp,
        Huen,
        Huenpp2,
        Dpm2,
        Dpm2Ancestral,
        Lms,
        DpmFast,
        DpmAdaptive,
        Dpmpp2sAncestral,
        DpmppSde,
        DpmppSdeGpu,
        Dpmpp2m,
        Dpmpp2mSde,
        Dpmpp2mSdeGpu,
        Dpmpp3mSde,
        Dpmpp3mSdeGpu,
        Ddpm,
        Lcm,
        Ddim,
        UniPc,
        UniPcBh2;
    }

    public static enum AiTaskType
    {
        TextToImage(1),
        ImageToImage(2),
        ImageUpscale(3),
        ImageWithMask(4),
        ImageToText(5),
        TextToAudio(6),
        TextToSpeech(7),
        SpeechToText(8);

        private final int value;
        AiTaskType(final int intValue) { value = intValue; }
        public int getValue() { return value; }
    }

    public static enum ComfyMaskSource
    {
        Red,
        Blue,
        Green,
        Alpha;
    }

    public static enum ModelType
    {
        TextToImage,
        TextEncoder,
        ImageUpscale,
        TextToSpeech,
        TextToAudio,
        SpeechToText,
        ImageToText,
        ImageToImage,
        ImageWithMask,
        Vae;
    }

    public static enum MediaTransformTaskType
    {
        ImageScale,
        VideoScale,
        ImageConvert,
        AudioConvert,
        VideoConvert,
        ImageCrop,
        VideoCrop,
        VideoCut,
        AudioCut,
        WatermarkImage,
        WatermarkVideo;
    }

    @DataContract
    public static enum MediaOutputFormat
    {
        MP4,
        Avi,
        Mkv,
        Mov,
        WebM,
        Gif,
        MP3,
        Wav,
        Flac;
    }

    public static enum AiProviderType
    {
        OpenAiProvider,
        GoogleAiProvider;
    }

    /**
    * The tool calls generated by the model, such as function calls.
    */
    @DataContract
    public static class ToolCall
    {
        /**
        * The ID of the tool call.
        */
        @DataMember(Name="id")
        @SerializedName("id")
        public String id = null;

        /**
        * The type of the tool. Currently, only `function` is supported.
        */
        @DataMember(Name="type")
        @SerializedName("type")
        public String type = null;

        /**
        * The function that the model called.
        */
        @DataMember(Name="function")
        @SerializedName("function")
        public String function = null;
        
        public String getId() { return id; }
        public ToolCall setId(String value) { this.id = value; return this; }
        public String getType() { return type; }
        public ToolCall setType(String value) { this.type = value; return this; }
        public String getFunction() { return function; }
        public ToolCall setFunction(String value) { this.function = value; return this; }
    }

    public static enum ResponseFormat
    {
        Text,
        JsonObject;
    }

    public static enum OpenAiToolType
    {
        Function;
    }

    @DataContract
    public static class OllamaModelDetails
    {
        @DataMember(Name="parent_model")
        @SerializedName("parent_model")
        public String parentModel = null;

        @DataMember(Name="format")
        @SerializedName("format")
        public String format = null;

        @DataMember(Name="family")
        @SerializedName("family")
        public String family = null;

        @DataMember(Name="families")
        @SerializedName("families")
        public ArrayList<String> families = null;

        @DataMember(Name="parameter_size")
        @SerializedName("parameter_size")
        public String parameterSize = null;

        @DataMember(Name="quantization_level")
        @SerializedName("quantization_level")
        public String quantizationLevel = null;
        
        public String getParentModel() { return parentModel; }
        public OllamaModelDetails setParentModel(String value) { this.parentModel = value; return this; }
        public String getFormat() { return format; }
        public OllamaModelDetails setFormat(String value) { this.format = value; return this; }
        public String getFamily() { return family; }
        public OllamaModelDetails setFamily(String value) { this.family = value; return this; }
        public ArrayList<String> getFamilies() { return families; }
        public OllamaModelDetails setFamilies(ArrayList<String> value) { this.families = value; return this; }
        public String getParameterSize() { return parameterSize; }
        public OllamaModelDetails setParameterSize(String value) { this.parameterSize = value; return this; }
        public String getQuantizationLevel() { return quantizationLevel; }
        public OllamaModelDetails setQuantizationLevel(String value) { this.quantizationLevel = value; return this; }
    }

    @DataContract
    public static class ChoiceMessage
    {
        /**
        * The contents of the message.
        */
        @DataMember(Name="content")
        @SerializedName("content")
        public String content = null;

        /**
        * The tool calls generated by the model, such as function calls.
        */
        @DataMember(Name="tool_calls")
        @SerializedName("tool_calls")
        public ArrayList<ToolCall> toolCalls = null;

        /**
        * The role of the author of this message.
        */
        @DataMember(Name="role")
        @SerializedName("role")
        public String role = null;
        
        public String getContent() { return content; }
        public ChoiceMessage setContent(String value) { this.content = value; return this; }
        public ArrayList<ToolCall> getToolCalls() { return toolCalls; }
        public ChoiceMessage setToolCalls(ArrayList<ToolCall> value) { this.toolCalls = value; return this; }
        public String getRole() { return role; }
        public ChoiceMessage setRole(String value) { this.role = value; return this; }
    }

}
