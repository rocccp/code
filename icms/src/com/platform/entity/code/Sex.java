package com.platform.entity.code;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.platform.entity.base.BaseCodeEntity;

@Component
@Entity
@Table(name = "CODE_PLATFORM_SEX")
public class Sex extends BaseCodeEntity
{
	private static final long serialVersionUID = -3525397407097536920L;
	
}
