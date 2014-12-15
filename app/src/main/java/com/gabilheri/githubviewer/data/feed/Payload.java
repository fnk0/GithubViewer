package com.gabilheri.githubviewer.data.feed;

import com.gabilheri.simpleorm.annotations.OrmField;
import com.gabilheri.simpleorm.annotations.Table;
import com.gabilheri.simpleorm.builders.OrmObject;
import com.google.gson.annotations.SerializedName;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 11/23/14.
 */

@Table(name = "payloads_table")
public class Payload extends OrmObject {

    @OrmField(name = "action")
    @SerializedName("action")
    private String action;

    @OrmField(name = "ref_type")
    @SerializedName("ref_type")
    private String refType;

    @OrmField(name = "description")
    @SerializedName("description")
    private String description;

    @OrmField(name = "pusher_type")
    @SerializedName("pusher_type")
    private String user;

    @OrmField(name = "member", foreignKey = true, referenceTable = "payloads_member_table")
    @SerializedName("member")
    private PayloadMember member;

    @OrmField(name = "push_id")
    @SerializedName("push_id")
    private long pushId;

    @OrmField(name = "size")
    @SerializedName("size")
    private int size;

    @OrmField(name = "distinct_size")
    @SerializedName("distinct_size")
    private int distinctSize;

    @OrmField(name = "ref")
    @SerializedName("ref")
    private String ref;

    @OrmField(name = "head")
    @SerializedName("head")
    private String head;

    @OrmField(name = "before")
    @SerializedName("before")
    private String before;

    public Payload() {
    }

    public String getRefType() {
        return refType;
    }

    public String getDescription() {
        return description;
    }

    public String getUser() {
        return user;
    }

    public PayloadMember getMember() {
        return member;
    }

    public String getAction() {
        return action;
    }

    public long getPushId() {
        return pushId;
    }

    public int getDistinctSize() {
        return distinctSize;
    }

    public String getRef() {
        return ref;
    }

    public String getHead() {
        return head;
    }

    public String getBefore() {
        return before;
    }
}
