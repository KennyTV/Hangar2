<#import "/spring.ftl" as spring />
<#import "*/utils/hangar.ftlh" as hangar />
<#import "*/layout/base.ftlh" as base />

@import java.time.OffsetDateTime

@import controllers.sugar.Requests.OreRequest
@import models.viewhelper.ProjectData
@import ore.OreConfig
@import util.StringFormatterUtils._
@(p: ProjectData)(implicit messages: Messages, request: OreRequest[_], config: OreConfig, flash: Flash, assetsFinder: AssetsFinder)

@projectRoutes = @{controllers.project.routes.Projects}

<#assign message><@spring.message "project.flag.plural" /></#assign>
<@base.base title="${message}">
    <div class="row">
        <div class="col-md-12  header-flags">
            <div class="clearfix">
                <h1 class="pull-left"><@spring.message "project.flag.plural" /> for <a href="@projectRoutes.show(p.project.ownerName, p.project.slug)">@p.project.ownerName/@p.project.slug</a></h1>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <#if p.flagCount == 0>
                <div class="alert-review alert alert-info" role="alert">
                    <i class="fas fa-info-circle"></i>
                    No flags found
                </div>
            <#else>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title pull-left"><@spring.message "project.flag.plural" /></h4>
                        <div class="clearfix"></div>
                    </div>
                    <table class="table table-condensed setting-no-border table-review-log">
                        <thead>
                            <tr>
                                <th>Submitter</th>
                                <th>Reason</th>
                                <th>When</th>
                                <th>Resolved</th>
                            </tr>
                        </thead>
                        <tbody>
                        @p.flags.map { case (flag, by, resolvedBy) =>
                        <tr>
                            <td>${by}</td>
                            <td>${flag.reason}, ${flag.comment}</td>
                            <td>${prettifyDateAndTime(flag.createdAt)}</td>
                            <#if flag.isResolved>
                                <td>${resolvedBy.get}
                                    at ${prettifyDateAndTime(flag.resolvedAt.getOrElse(OffsetDateTime.MIN))}</td>
                            <#else>
                                <td>-not resolved-</td>
                            </#if>
                        </tr>
                        }
                        </tbody>
                    </table>
             </#if>
            </div>
        </div>
    </div>
</@base.base>