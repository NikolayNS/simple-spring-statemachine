package com.dmitrenko.simplespringstatemachine.model.entity;

import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Data
@Accessors(chain = true)
@NoArgsConstructor
@TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType.class)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Table(name = "task")
public class Task extends BaseEntity {

    @Type(type = "pgsql_enum")
    @Enumerated(value = EnumType.STRING)
    @Column(name = "task_state", nullable = false)
    private TaskState state;

    @Column(name = "error_message")
    private String errorMessage;
}
