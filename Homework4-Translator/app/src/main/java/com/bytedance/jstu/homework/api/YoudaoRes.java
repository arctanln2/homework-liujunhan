package com.bytedance.jstu.homework.api;

import java.util.List;

public class YoudaoRes {

    public class WebTrans {
        private List<WebTranslation> webtranslation;
        public class WebTranslation {
            private String value;

            public String getValue() {return value;}
            public void setValue(String value) {this.value = value;}
        }

        public List<WebTranslation> getWebTranslation() {return webtranslation;}
        public void setWebTranslation(List<WebTranslation> webTranslation) {this.webtranslation = webTranslation;}
    }

    private WebTrans web_trans;

    public WebTrans getWebTrans() {return web_trans;}
    public void setWebTrans(WebTrans webTrans) {this.web_trans = webTrans;}
}
