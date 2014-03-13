package com.platform.entity.uniauth.org;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.platform.entity.base.BaseCodeEntity;

@Entity
@Table(name = "CODE_PLATFORM_ORG_TYPE")
@Component
public class OrgType extends BaseCodeEntity 
{
	private static final long serialVersionUID = 6441557609397963674L;

}
