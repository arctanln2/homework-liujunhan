package com.bytedance.jstu.homework.api;

import java.util.List;

public class Youdao {

    public class Meta {
        private List<String> dicts;

        public List<String> getDicts() {return dicts;}
        public void setDicts(List<String> dicts) {this.dicts = dicts;}
    }

    private Meta meta;

    public Meta getMeta() {return meta;}
    public void setMeta(Meta meta) {this.meta = meta;}
}
