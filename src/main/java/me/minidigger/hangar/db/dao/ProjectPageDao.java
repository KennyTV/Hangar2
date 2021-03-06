package me.minidigger.hangar.db.dao;

import me.minidigger.hangar.db.model.ProjectPagesTable;
import me.minidigger.hangar.model.viewhelpers.ProjectPage;
import org.jdbi.v3.sqlobject.config.KeyColumn;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.customizer.Timestamped;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
@RegisterBeanMapper(ProjectPagesTable.class)
public interface ProjectPageDao {

    @SqlUpdate("INSERT INTO project_pages (created_at, project_id, name, slug, contents, is_deletable, parent_id) VALUES (:now, :projectId, :name, :slug, :contents, :isDeletable, :parentId)")
    @Timestamped
    @GetGeneratedKeys
    ProjectPagesTable insert(@BindBean ProjectPagesTable projectPagesTable);

    @SqlUpdate("UPDATE project_pages SET contents = :contents WHERE id = :id")
    @GetGeneratedKeys
    ProjectPagesTable update(@BindBean ProjectPagesTable projectPagesTable);

    @SqlUpdate("DELETE FROM project_pages WHERE id = :id")
    void delete(@BindBean ProjectPagesTable projectPagesTable);

    @SqlQuery("SELECT * FROM project_pages WHERE project_id = :projectId AND (lower(slug) = lower(:pageName) OR id = :pageId)")
    ProjectPagesTable getPage(long projectId, String pageName, Long pageId);

    @SqlQuery("WITH RECURSIVE parents AS (SELECT * FROM project_pages WHERE project_id = :projectId AND (name = :pageName OR id = :pageId) UNION SELECT pp.* FROM project_pages pp INNER JOIN parents par ON par.id = pp.parent_id) SELECT * FROM parents")
    List<ProjectPagesTable> getPageParents(long projectId, String pageName, Long pageId);

    @RegisterBeanMapper(ProjectPage.class)
    @KeyColumn("id")
    @SqlQuery("SELECT id, created_at, name, slug, contents, is_deletable FROM project_pages WHERE project_id = :projectId AND parent_id IS NULL ORDER BY created_at")
    LinkedHashMap<Long, ProjectPage> getRootPages(long projectId);

    @RegisterBeanMapper(ProjectPage.class)
    @SqlQuery("SELECT id, created_at, name, slug, contents, is_deletable, parent_id FROM project_pages WHERE project_id = :projectId AND parent_id = :parentId")
    List<ProjectPage> getChildPages(long projectId, long parentId);
}
