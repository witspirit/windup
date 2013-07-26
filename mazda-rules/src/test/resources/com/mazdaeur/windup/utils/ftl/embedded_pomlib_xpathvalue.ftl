<windup:xpath-value
        <#if parentPomlib.hasDefaultScope()>
        xpath="//pom:dependency/pom:artifactId/text()[. = '${embeddedPomlib.id.name}' and ./../../pom:groupId/text() = '${embeddedPomlib.id.group}' and //pom:dependency/pom:artifactId/text()[. = '${parentPomlib.id.name}' and ./../../pom:groupId/text() = '${parentPomlib.id.group}']]"
        <#else>
        xpath="//pom:dependency/pom:artifactId/text()[. = '${embeddedPomlib.id.name}' and ./../../pom:scope/text() = '${parentPomlib.scope}' and ./../../pom:groupId/text() = '${embeddedPomlib.id.group}' and //pom:dependency/pom:artifactId/text()[. = '${parentPomlib.id.name}' and ./../../pom:groupId/text() = '${parentPomlib.id.group}']]"
        </#if>
        description="${embeddedPomlib.id.name} is already included in ${parentPomlib.id.name}. Consider removing this redundant dependency."
        effort="1"
        inline="true">
    <windup:namespace prefix="pom" uri="http://maven.apache.org/POM/4.0.0" />
</windup:xpath-value>
