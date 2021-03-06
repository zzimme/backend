package com.marine.backend.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class BaseEntity implements Serializable{

	private static final long serialVersionUID = -283429122719578794L;

	@CreatedDate
    @Column(name = "register_date", nullable=false, columnDefinition="DATETIME default CURRENT_TIMESTAMP", updatable = false)
	private LocalDateTime registerDate;
}
