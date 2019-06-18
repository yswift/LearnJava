package io.webapi.eol;

public class SchoolInfo {
    /**
     * school_id : 35
     * data_code : 532000100
     * name : 云南大学
     * type : 5000
     * school_type : 6000
     * school_nature : 36000
     * level : 2001
     * code_enroll : 1067300
     * belong : 云南省教育厅
     * f985 : 2
     * f211 : 1
     * department : 2
     * admissions : 1
     * central : 2
     * dual_class : 38001
     * is_seal : 1
     * num_subject : 7
     * num_master : 42
     * num_doctor : 21
     * num_academician : 0
     * num_library : 360
     * num_lab : 2
     * province_id : 53
     * city_id : 5301
     * county_id : 530114
     * is_ads : 1
     * is_recruitment : 1
     * create_date : 1922
     * area : 4363.64
     * old_name :
     * status : 1
     * add_id : 0
     * add_time : 2018-12-08 17:23:21
     * update_id : 444
     * update_time : 2019-06-05 19:44:54
     * ad_level : 1
     * short : ynu,云大
     * e_pc : 1
     * e_app : 1
     * ruanke_rank : 121
     * logo : /app/html/upload/logo/35.jpg
     * level_name : 普通本科
     * type_name : 综合类
     * school_type_name : 普通本科
     * school_nature_name : 公办
     * dual_class_name : 双一流
     * province_name : 云南
     * city_name : 昆明市
     * town_name : 呈贡区
     * weiwangzhan :
     * urllinks : {"71005":{"school_id":"35","link":"https://m-view.eol.cn/h5/zsgk/answer_noresult.html?t=1111","type":"71005"},"71006":{"school_id":"35","link":"https://s11.cnzz.com/z_stat.php?id=1254838040&web_id=1254838040","type":"71006"},"71004":{"school_id":"35","link":"https://souky.eol.cn/HomePage/index_202.html","type":"71004"}}
     * yjszs : https://souky.eol.cn/HomePage/index_202.html
     * xiaoyuan :
     * email : zszx@ynu.edu.cn
     * address : 云南省昆明市翠湖北路2号
     * postcode : 650091
     * site : http://www.ynu.edu.cn/
     * phone : 0871-65033819,0871-65032173,0871-65939873
     * content : 云南大学始建于1922年，时为私立东陆大学，1934年更名为省立云南大学，1938年改为国立云南大学，是我国西部边疆最早建立的综合性大学之一。1937年，著名数学家、教育家熊庆来出任校长，
     * video : {"school_id":"35","url":"5285890782031228184","url_type":"2","img_url":"https://static-data.eol.cn//oldupload/userfiles/Image/images/15401984139271xe.png"}
     * is_video : 1
     */

    private String school_id;
    private String data_code;
    private String name;
    private String type;
    private String school_type;
    private String school_nature;
    private String level;
    private String code_enroll;
    private String belong;
    private String f985;
    private String f211;
    private String department;
    private String admissions;
    private String central;
    private String dual_class;
    private String is_seal;
    private String num_subject;
    private String num_master;
    private String num_doctor;
    private String num_academician;
    private String num_library;
    private String num_lab;
    private String province_id;
    private String city_id;
    private String county_id;
    private String is_ads;
    private String is_recruitment;
    private String create_date;
    private String area;
    private String old_name;
    private String status;
    private String add_id;
    private String add_time;
    private String update_id;
    private String update_time;
    private String ad_level;
    @com.google.gson.annotations.SerializedName("short")
    private String shortX;
    private String e_pc;
    private String e_app;
    private String ruanke_rank;
    private String logo;
    private String level_name;
    private String type_name;
    private String school_type_name;
    private String school_nature_name;
    private String dual_class_name;
    private String province_name;
    private String city_name;
    private String town_name;
    private String weiwangzhan;
    private UrllinksBean urllinks;
    private String yjszs;
    private String xiaoyuan;
    private String email;
    private String address;
    private String postcode;
    private String site;
    private String phone;
    private String content;
    private VideoBean video;
    private int is_video;

    public String getSchool_id() {
        return school_id;
    }

    public void setSchool_id(String school_id) {
        this.school_id = school_id;
    }

    public String getData_code() {
        return data_code;
    }

    public void setData_code(String data_code) {
        this.data_code = data_code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSchool_type() {
        return school_type;
    }

    public void setSchool_type(String school_type) {
        this.school_type = school_type;
    }

    public String getSchool_nature() {
        return school_nature;
    }

    public void setSchool_nature(String school_nature) {
        this.school_nature = school_nature;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCode_enroll() {
        return code_enroll;
    }

    public void setCode_enroll(String code_enroll) {
        this.code_enroll = code_enroll;
    }

    public String getBelong() {
        return belong;
    }

    public void setBelong(String belong) {
        this.belong = belong;
    }

    public String getF985() {
        return f985;
    }

    public void setF985(String f985) {
        this.f985 = f985;
    }

    public String getF211() {
        return f211;
    }

    public void setF211(String f211) {
        this.f211 = f211;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getAdmissions() {
        return admissions;
    }

    public void setAdmissions(String admissions) {
        this.admissions = admissions;
    }

    public String getCentral() {
        return central;
    }

    public void setCentral(String central) {
        this.central = central;
    }

    public String getDual_class() {
        return dual_class;
    }

    public void setDual_class(String dual_class) {
        this.dual_class = dual_class;
    }

    public String getIs_seal() {
        return is_seal;
    }

    public void setIs_seal(String is_seal) {
        this.is_seal = is_seal;
    }

    public String getNum_subject() {
        return num_subject;
    }

    public void setNum_subject(String num_subject) {
        this.num_subject = num_subject;
    }

    public String getNum_master() {
        return num_master;
    }

    public void setNum_master(String num_master) {
        this.num_master = num_master;
    }

    public String getNum_doctor() {
        return num_doctor;
    }

    public void setNum_doctor(String num_doctor) {
        this.num_doctor = num_doctor;
    }

    public String getNum_academician() {
        return num_academician;
    }

    public void setNum_academician(String num_academician) {
        this.num_academician = num_academician;
    }

    public String getNum_library() {
        return num_library;
    }

    public void setNum_library(String num_library) {
        this.num_library = num_library;
    }

    public String getNum_lab() {
        return num_lab;
    }

    public void setNum_lab(String num_lab) {
        this.num_lab = num_lab;
    }

    public String getProvince_id() {
        return province_id;
    }

    public void setProvince_id(String province_id) {
        this.province_id = province_id;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getCounty_id() {
        return county_id;
    }

    public void setCounty_id(String county_id) {
        this.county_id = county_id;
    }

    public String getIs_ads() {
        return is_ads;
    }

    public void setIs_ads(String is_ads) {
        this.is_ads = is_ads;
    }

    public String getIs_recruitment() {
        return is_recruitment;
    }

    public void setIs_recruitment(String is_recruitment) {
        this.is_recruitment = is_recruitment;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getOld_name() {
        return old_name;
    }

    public void setOld_name(String old_name) {
        this.old_name = old_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAdd_id() {
        return add_id;
    }

    public void setAdd_id(String add_id) {
        this.add_id = add_id;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public String getUpdate_id() {
        return update_id;
    }

    public void setUpdate_id(String update_id) {
        this.update_id = update_id;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getAd_level() {
        return ad_level;
    }

    public void setAd_level(String ad_level) {
        this.ad_level = ad_level;
    }

    public String getShortX() {
        return shortX;
    }

    public void setShortX(String shortX) {
        this.shortX = shortX;
    }

    public String getE_pc() {
        return e_pc;
    }

    public void setE_pc(String e_pc) {
        this.e_pc = e_pc;
    }

    public String getE_app() {
        return e_app;
    }

    public void setE_app(String e_app) {
        this.e_app = e_app;
    }

    public String getRuanke_rank() {
        return ruanke_rank;
    }

    public void setRuanke_rank(String ruanke_rank) {
        this.ruanke_rank = ruanke_rank;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getLevel_name() {
        return level_name;
    }

    public void setLevel_name(String level_name) {
        this.level_name = level_name;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public String getSchool_type_name() {
        return school_type_name;
    }

    public void setSchool_type_name(String school_type_name) {
        this.school_type_name = school_type_name;
    }

    public String getSchool_nature_name() {
        return school_nature_name;
    }

    public void setSchool_nature_name(String school_nature_name) {
        this.school_nature_name = school_nature_name;
    }

    public String getDual_class_name() {
        return dual_class_name;
    }

    public void setDual_class_name(String dual_class_name) {
        this.dual_class_name = dual_class_name;
    }

    public String getProvince_name() {
        return province_name;
    }

    public void setProvince_name(String province_name) {
        this.province_name = province_name;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getTown_name() {
        return town_name;
    }

    public void setTown_name(String town_name) {
        this.town_name = town_name;
    }

    public String getWeiwangzhan() {
        return weiwangzhan;
    }

    public void setWeiwangzhan(String weiwangzhan) {
        this.weiwangzhan = weiwangzhan;
    }

    public UrllinksBean getUrllinks() {
        return urllinks;
    }

    public void setUrllinks(UrllinksBean urllinks) {
        this.urllinks = urllinks;
    }

    public String getYjszs() {
        return yjszs;
    }

    public void setYjszs(String yjszs) {
        this.yjszs = yjszs;
    }

    public String getXiaoyuan() {
        return xiaoyuan;
    }

    public void setXiaoyuan(String xiaoyuan) {
        this.xiaoyuan = xiaoyuan;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public VideoBean getVideo() {
        return video;
    }

    public void setVideo(VideoBean video) {
        this.video = video;
    }

    public int getIs_video() {
        return is_video;
    }

    public void setIs_video(int is_video) {
        this.is_video = is_video;
    }

    @Override
    public String toString() {
        return "SchoolInfo{" +
                "school_id='" + school_id + '\'' +
                ", data_code='" + data_code + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", school_type='" + school_type + '\'' +
                ", school_nature='" + school_nature + '\'' +
                ", level='" + level + '\'' +
                ", code_enroll='" + code_enroll + '\'' +
                ", belong='" + belong + '\'' +
                ", f985='" + f985 + '\'' +
                ", f211='" + f211 + '\'' +
                ", department='" + department + '\'' +
                ", admissions='" + admissions + '\'' +
                ", central='" + central + '\'' +
                ", dual_class='" + dual_class + '\'' +
                ", is_seal='" + is_seal + '\'' +
                ", num_subject='" + num_subject + '\'' +
                ", num_master='" + num_master + '\'' +
                ", num_doctor='" + num_doctor + '\'' +
                ", num_academician='" + num_academician + '\'' +
                ", num_library='" + num_library + '\'' +
                ", num_lab='" + num_lab + '\'' +
                ", province_id='" + province_id + '\'' +
                ", city_id='" + city_id + '\'' +
                ", county_id='" + county_id + '\'' +
                ", is_ads='" + is_ads + '\'' +
                ", is_recruitment='" + is_recruitment + '\'' +
                ", create_date='" + create_date + '\'' +
                ", area='" + area + '\'' +
                ", old_name='" + old_name + '\'' +
                ", status='" + status + '\'' +
                ", add_id='" + add_id + '\'' +
                ", add_time='" + add_time + '\'' +
                ", update_id='" + update_id + '\'' +
                ", update_time='" + update_time + '\'' +
                ", ad_level='" + ad_level + '\'' +
                ", shortX='" + shortX + '\'' +
                ", e_pc='" + e_pc + '\'' +
                ", e_app='" + e_app + '\'' +
                ", ruanke_rank='" + ruanke_rank + '\'' +
                ", logo='" + logo + '\'' +
                ", level_name='" + level_name + '\'' +
                ", type_name='" + type_name + '\'' +
                ", school_type_name='" + school_type_name + '\'' +
                ", school_nature_name='" + school_nature_name + '\'' +
                ", dual_class_name='" + dual_class_name + '\'' +
                ", province_name='" + province_name + '\'' +
                ", city_name='" + city_name + '\'' +
                ", town_name='" + town_name + '\'' +
                ", weiwangzhan='" + weiwangzhan + '\'' +
                ", urllinks=" + urllinks +
                ", yjszs='" + yjszs + '\'' +
                ", xiaoyuan='" + xiaoyuan + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", postcode='" + postcode + '\'' +
                ", site='" + site + '\'' +
                ", phone='" + phone + '\'' +
                ", content='" + content + '\'' +
                ", video=" + video +
                ", is_video=" + is_video +
                '}';
    }

    public static class UrllinksBean {
        /**
         * 71005 : {"school_id":"35","link":"https://m-view.eol.cn/h5/zsgk/answer_noresult.html?t=1111","type":"71005"}
         * 71006 : {"school_id":"35","link":"https://s11.cnzz.com/z_stat.php?id=1254838040&web_id=1254838040","type":"71006"}
         * 71004 : {"school_id":"35","link":"https://souky.eol.cn/HomePage/index_202.html","type":"71004"}
         */

        @com.google.gson.annotations.SerializedName("71005")
        private _$71005Bean _$71005;
        @com.google.gson.annotations.SerializedName("71006")
        private _$71006Bean _$71006;
        @com.google.gson.annotations.SerializedName("71004")
        private _$71004Bean _$71004;

        public _$71005Bean get_$71005() {
            return _$71005;
        }

        public void set_$71005(_$71005Bean _$71005) {
            this._$71005 = _$71005;
        }

        public _$71006Bean get_$71006() {
            return _$71006;
        }

        public void set_$71006(_$71006Bean _$71006) {
            this._$71006 = _$71006;
        }

        public _$71004Bean get_$71004() {
            return _$71004;
        }

        public void set_$71004(_$71004Bean _$71004) {
            this._$71004 = _$71004;
        }

        @Override
        public String toString() {
            return "UrllinksBean{" +
                    "_$71005=" + _$71005 +
                    ", _$71006=" + _$71006 +
                    ", _$71004=" + _$71004 +
                    '}';
        }

        public static class _$71005Bean {
            /**
             * school_id : 35
             * link : https://m-view.eol.cn/h5/zsgk/answer_noresult.html?t=1111
             * type : 71005
             */

            private String school_id;
            private String link;
            private String type;

            public String getSchool_id() {
                return school_id;
            }

            public void setSchool_id(String school_id) {
                this.school_id = school_id;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }

        public static class _$71006Bean {
            /**
             * school_id : 35
             * link : https://s11.cnzz.com/z_stat.php?id=1254838040&web_id=1254838040
             * type : 71006
             */

            private String school_id;
            private String link;
            private String type;

            public String getSchool_id() {
                return school_id;
            }

            public void setSchool_id(String school_id) {
                this.school_id = school_id;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }

        public static class _$71004Bean {
            /**
             * school_id : 35
             * link : https://souky.eol.cn/HomePage/index_202.html
             * type : 71004
             */

            private String school_id;
            private String link;
            private String type;

            public String getSchool_id() {
                return school_id;
            }

            public void setSchool_id(String school_id) {
                this.school_id = school_id;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }
    }

    public static class VideoBean {
        /**
         * school_id : 35
         * url : 5285890782031228184
         * url_type : 2
         * img_url : https://static-data.eol.cn//oldupload/userfiles/Image/images/15401984139271xe.png
         */

        private String school_id;
        private String url;
        private String url_type;
        private String img_url;

        public String getSchool_id() {
            return school_id;
        }

        public void setSchool_id(String school_id) {
            this.school_id = school_id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUrl_type() {
            return url_type;
        }

        public void setUrl_type(String url_type) {
            this.url_type = url_type;
        }

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        @Override
        public String toString() {
            return "VideoBean{" +
                    "school_id='" + school_id + '\'' +
                    ", url='" + url + '\'' +
                    ", url_type='" + url_type + '\'' +
                    ", img_url='" + img_url + '\'' +
                    '}';
        }
    }
}
