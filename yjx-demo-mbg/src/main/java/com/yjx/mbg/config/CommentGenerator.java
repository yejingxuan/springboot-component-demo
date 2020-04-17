package com.yjx.mbg.config;

import java.util.Date;
import java.util.Properties;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.internal.DefaultCommentGenerator;

/**
 * 自定义注释生成器
 * Created by macro on 2018/4/26.
 */
@Slf4j
public class CommentGenerator extends DefaultCommentGenerator {

    private static final String JAVAX_PERSISTENCE_COLUMN_FULL_CLASS_NAME="javax.persistence.Column";
    private static final String JAVAX_PERSISTENCE_TABLE_FULL_CLASS_NAME="javax.persistence.Table";
    private static final String JAVAX_PERSISTENCE_ID_FULL_CLASS_NAME="javax.persistence.Id";
    private static final String LOMBOK_DATA_FULL_CLASS_NAME="lombok.Data";

    /**
     * 设置用户配置的参数
     */
    @Override
    public void addConfigurationProperties(Properties properties) {
        super.addConfigurationProperties(properties);
    }

    /**
     * 给字段添加注释
     */
    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable,
                                IntrospectedColumn introspectedColumn) {
        field.addJavaDocLine("/**");
        //获取数据库字段的备注信息
        field.addJavaDocLine(" * "+introspectedColumn.getRemarks());
        field.addJavaDocLine(" */");
        try {
            String primaryKey = introspectedTable.getPrimaryKeyColumns().get(0).getActualColumnName();
            if(introspectedColumn.getActualColumnName().equals(primaryKey)){
                field.addJavaDocLine("@Id");
            }
        } catch (Exception e){
            log.error("获取主键失败：{}", introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime());
        }

        field.addJavaDocLine("@Column(name = \""+introspectedColumn.getActualColumnName()+"\")");
    }


    /**
     * 添加引入的包
     * @param compilationUnit
     */
    @Override
    public void addJavaFileComment(CompilationUnit compilationUnit) {
        super.addJavaFileComment(compilationUnit);

        compilationUnit.addImportedType(new FullyQualifiedJavaType(JAVAX_PERSISTENCE_COLUMN_FULL_CLASS_NAME));
        compilationUnit.addImportedType(new FullyQualifiedJavaType(JAVAX_PERSISTENCE_TABLE_FULL_CLASS_NAME));
        compilationUnit.addImportedType(new FullyQualifiedJavaType(JAVAX_PERSISTENCE_ID_FULL_CLASS_NAME));
        compilationUnit.addImportedType(new FullyQualifiedJavaType(LOMBOK_DATA_FULL_CLASS_NAME));
    }

    /**
     * 给model添加注释
     * @param topLevelClass
     * @param introspectedTable
     */
    @Override
    public void addModelClassComment(TopLevelClass topLevelClass,
            IntrospectedTable introspectedTable) {
        super.addModelClassComment(topLevelClass, introspectedTable);
        //添加domain的注释
        topLevelClass.addJavaDocLine("/**");
        topLevelClass.addJavaDocLine("* @author: jxye ");
        topLevelClass.addJavaDocLine("*/");
        topLevelClass.addJavaDocLine("@Table(name = \""+introspectedTable.getFullyQualifiedTableNameAtRuntime()+"\")");
    }




}
