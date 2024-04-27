package com.cc.generator.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 
 * @TableName request
 */
@TableName(value ="request")
public class Request implements Serializable {
    /**
     * 
     */
    @TableId
    private Integer id;

    /**
     * 
     */
    private String mapping;

    /**
     * 
     */
    private String contentText;

    /**
     * 
     */
    private byte[] contentBlob;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     */
    public String getMapping() {
        return mapping;
    }

    /**
     * 
     */
    public void setMapping(String mapping) {
        this.mapping = mapping;
    }

    /**
     * 
     */
    public String getContentText() {
        return contentText;
    }

    /**
     * 
     */
    public void setContentText(String contentText) {
        this.contentText = contentText;
    }

    /**
     * 
     */
    public byte[] getContentBlob() {
        return contentBlob;
    }

    /**
     * 
     */
    public void setContentBlob(byte[] contentBlob) {
        this.contentBlob = contentBlob;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Request other = (Request) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMapping() == null ? other.getMapping() == null : this.getMapping().equals(other.getMapping()))
            && (this.getContentText() == null ? other.getContentText() == null : this.getContentText().equals(other.getContentText()))
            && (Arrays.equals(this.getContentBlob(), other.getContentBlob()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMapping() == null) ? 0 : getMapping().hashCode());
        result = prime * result + ((getContentText() == null) ? 0 : getContentText().hashCode());
        result = prime * result + (Arrays.hashCode(getContentBlob()));
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", mapping=").append(mapping);
        sb.append(", contentText=").append(contentText);
        sb.append(", contentBlob=").append(contentBlob);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}