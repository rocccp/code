package com.platform.entity.code;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.platform.entity.base.BaseCodeEntity;

@Component
@Entity
@Table(name = "CODE_PLATFORM_CERTIFICATETYPE")
public class CertificateType extends BaseCodeEntity
{
	private static final long serialVersionUID = -7265561114805207223L;

}
