package caojun.com.myapplication.retrofit;

import caojun.com.myapplication.model.GanHuo;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by tiger on 2017/3/6.
 */

public interface GankServer {

    @GET("api/data/{type}/{count}/{page}")
    Observable<GanHuo> getGanHuo(
            @Path("type") String type,
            @Path("count") int count,
            @Path("page")  int page
     );


}
