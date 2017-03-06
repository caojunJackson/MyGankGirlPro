package caojun.com.myapplication.model;

import java.util.List;

/**
 * Created by tiger on 2017/3/6.
 */

public class GanHuo {


    /**
     * error : false
     * results : [{"_id":"58bcae33421aa90efc4fb666","createdAt":"2017-03-06T08:32:51.490Z",
     * "desc":"3-6","publishedAt":"2017-03-06T11:17:33.140Z","source":"chrome","type":"福利",
     * "url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-03-06-tumblr_ombicf0KXi1vpybydo6_540
     * .jpg","used":true,"who":"daimajia"},{"_id":"58b88a2f421aa90efc4fb649",
     * "createdAt":"2017-03-03T05:10:07.940Z","desc":"3-3","publishedAt":"2017-03-03T12:13:36
     * .333Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn
     * .com/2017-03-02-16906481_1495916493759925_5770648570629718016_n.jpg","used":true,
     * "who":"daimajia"},{"_id":"58b76cb9421aa90f131785e4","createdAt":"2017-03-02T08:52:09
     * .811Z","desc":"3-02","publishedAt":"2017-03-02T11:51:29.672Z","source":"chrome",
     * "type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn
     * .com/2017-03-02-17077393_314135475655975_7855162741030387712_n.jpg","used":true,
     * "who":"代码家"},{"_id":"58b60881421aa90efc4fb627","createdAt":"2017-03-01T07:32:17.106Z",
     * "desc":"3-1","publishedAt":"2017-03-01T12:03:57.461Z","source":"chrome","type":"福利",
     * "url":"http://7xi8d6.com1.z0.glb.clouddn
     * .com/2017-02-28-15057157_446684252387131_4267811446148038656_n.jpg","used":true,
     * "who":"daimajia"},{"_id":"58b4b764421aa90efc4fb61b","createdAt":"2017-02-28T07:33:56
     * .976Z","desc":"2-28","publishedAt":"2017-02-28T11:45:44.815Z","source":"chrome",
     * "type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn
     * .com/2017-02-27-tumblr_om1aowIoKa1qbw5qso1_540.jpg","used":true,"who":"daimajia"}]
     */

    private boolean error;
    /**
     * _id : 58bcae33421aa90efc4fb666
     * createdAt : 2017-03-06T08:32:51.490Z
     * desc : 3-6
     * publishedAt : 2017-03-06T11:17:33.140Z
     * source : chrome
     * type : 福利
     * url : http://7xi8d6.com1.z0.glb.clouddn.com/2017-03-06-tumblr_ombicf0KXi1vpybydo6_540.jpg
     * used : true
     * who : daimajia
     */

    private List<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }

        @Override
        public String toString() {
            return "ResultsBean{" +
                    "_id='" + _id + '\'' +
                    ", createdAt='" + createdAt + '\'' +
                    ", desc='" + desc + '\'' +
                    ", publishedAt='" + publishedAt + '\'' +
                    ", source='" + source + '\'' +
                    ", type='" + type + '\'' +
                    ", url='" + url + '\'' +
                    ", used=" + used +
                    ", who='" + who + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (!(o instanceof ResultsBean))
                return false;

            ResultsBean that = (ResultsBean) o;

            if (isUsed() != that.isUsed())
                return false;
            if (get_id() != null ? !get_id().equals(that.get_id()) : that.get_id() != null)
                return false;
            if (getCreatedAt() != null ? !getCreatedAt().equals(that.getCreatedAt()) : that
                    .getCreatedAt() != null)
                return false;
            if (getDesc() != null ? !getDesc().equals(that.getDesc()) : that.getDesc() != null)
                return false;
            if (getPublishedAt() != null ? !getPublishedAt().equals(that.getPublishedAt()) : that
                    .getPublishedAt() != null)
                return false;
            if (getSource() != null ? !getSource().equals(that.getSource()) : that.getSource() !=
                    null)
                return false;
            if (getType() != null ? !getType().equals(that.getType()) : that.getType() != null)
                return false;
            if (getUrl() != null ? !getUrl().equals(that.getUrl()) : that.getUrl() != null)
                return false;
            return getWho() != null ? getWho().equals(that.getWho()) : that.getWho() == null;

        }

        @Override
        public int hashCode() {
            int result = get_id() != null ? get_id().hashCode() : 0;
            result = 31 * result + (getCreatedAt() != null ? getCreatedAt().hashCode() : 0);
            result = 31 * result + (getDesc() != null ? getDesc().hashCode() : 0);
            result = 31 * result + (getPublishedAt() != null ? getPublishedAt().hashCode() : 0);
            result = 31 * result + (getSource() != null ? getSource().hashCode() : 0);
            result = 31 * result + (getType() != null ? getType().hashCode() : 0);
            result = 31 * result + (getUrl() != null ? getUrl().hashCode() : 0);
            result = 31 * result + (isUsed() ? 1 : 0);
            result = 31 * result + (getWho() != null ? getWho().hashCode() : 0);
            return result;
        }
    }
}
