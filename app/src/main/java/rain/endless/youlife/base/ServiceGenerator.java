package rain.endless.youlife.base;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public class ServiceGenerator {
    private static Retrofit.Builder builder=new Retrofit.Builder().baseUrl(Constants.URL_BASE);

//    public static <S> S createService(Class<S> classService)
//    {
//        OkHttpClient.Builder builder
//    }

}
