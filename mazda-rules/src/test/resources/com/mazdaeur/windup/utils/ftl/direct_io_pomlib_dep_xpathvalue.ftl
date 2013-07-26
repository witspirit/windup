<windup:xpath-value
        xpath="//pom:dependency/pom:artifactId/text()[. = '${depId.name}' and ./../../pom:groupId/text() = '${depId.group}']"
        <#if pomlibs?size = 1>
            description="This dependency should probably not be used directly. Consider this POM lib instead: ${pomlibs?first}"
        <#else>
            description="This dependency should probably not be used directly. Consider one of these POM libs instead: ${pomlibs?join(", ")}"
        </#if>
        effort="1"
        inline="true">
    <windup:namespace prefix="pom" uri="http://maven.apache.org/POM/4.0.0" />
</windup:xpath-value>
