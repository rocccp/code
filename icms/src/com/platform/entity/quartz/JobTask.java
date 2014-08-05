package com.platform.entity.quartz;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.platform.entity.base.BaseEntity;

@Entity
@Table(name = "T_TASK_JOB")
@Component
public class JobTask extends BaseEntity
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private int id;

    private String name;
    
    private Date begin;


}
