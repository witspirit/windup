<windup:xpath-value
        xpath="//pom:dependency[./pom:artifactId/text() =  '${pomlibId.name}' and ./pom:groupId/text() = '${pomlibId.group}']/pom:version/text()[. != '${r"${pomlib.version}"}']"
        description="Inappropriate version reference ! You should use ${r"${pomlib.version}"} to appropriately get the project root's version of the POM Lib"
        effort="1"
        inline="true">
    <windup:namespace prefix="pom" uri="http://maven.apache.org/POM/4.0.0" />
</windup:xpath-value>
