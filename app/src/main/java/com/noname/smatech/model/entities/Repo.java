package com.noname.smatech.model.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import static com.noname.smatech.model.usecases.PublicusecasesKt.getDate;

@Entity(tableName = "Repositry")
public class Repo {


    @PrimaryKey(autoGenerate = true)
    private int repoid;


    @ColumnInfo(name = "nodeid")
    private String node_id = null;

    @ColumnInfo(name = "name")
    private String full_name = null;

    @ColumnInfo(name = "url")
    private String url = null;

    @ColumnInfo(name = "created")
    private String created_at = null;

    @ColumnInfo(name = "modified")
    private String updated_at = null;

    public int getRepoid() {
        return repoid;
    }

    public void setRepoid(int repoid) {
        this.repoid = repoid;
    }

    public String getNode_id() {
        return node_id;
    }

    public void setNode_id(String node_id) {
        this.node_id = node_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCreated_at() {
        return getDate(created_at);
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return getDate(updated_at);
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
