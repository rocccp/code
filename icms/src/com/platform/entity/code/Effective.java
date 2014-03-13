package com.platform.entity.code;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.platform.entity.base.BaseCodeEntity;

@Component
@Entity
@Table(name = "CODE_PLATFORM_EFFECTIVE")
public class Effective extends BaseCodeEntity
{
	private static final long serialVersionUID = -7447182000442145386L;


}
