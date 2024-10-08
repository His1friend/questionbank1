package org.homework.questions_bank.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName knowledge_edge
 */
@TableName(value ="knowledge_edge")
@Data
public class KnowledgeEdge implements Serializable {
    /**
     * 
     */
    private Integer sourceKid;

    /**
     * 
     */
    private Integer targetKid;

    /**
     * 
     */
    private String relation;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

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
        KnowledgeEdge other = (KnowledgeEdge) that;
        return (this.getSourceKid() == null ? other.getSourceKid() == null : this.getSourceKid().equals(other.getSourceKid()))
            && (this.getTargetKid() == null ? other.getTargetKid() == null : this.getTargetKid().equals(other.getTargetKid()))
            && (this.getRelation() == null ? other.getRelation() == null : this.getRelation().equals(other.getRelation()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSourceKid() == null) ? 0 : getSourceKid().hashCode());
        result = prime * result + ((getTargetKid() == null) ? 0 : getTargetKid().hashCode());
        result = prime * result + ((getRelation() == null) ? 0 : getRelation().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", sourceKid=").append(sourceKid);
        sb.append(", targetKid=").append(targetKid);
        sb.append(", relation=").append(relation);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}