<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:cv="http://univrouen.fr/cv24v1/model">
    <xsl:output method="html" version="1.0" indent="yes"/>
    <xsl:template match="/">
        <html>
            <head>
                <title>CV Resume</title>
                <link rel="stylesheet" type="text/css" href="/styles/stylecv.css"/>
                <script src="/js/script.js"></script>
            </head>
            <body>
                <h1>CV Resume</h1>
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Genre</th>
                            <th>Nom</th>
                            <th>Prénom</th>
                            <th>Objectif</th>
                            <th>Statut</th>
                            <th>Diplôme</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <xsl:for-each select="cv:cvs/cv:cvResume">
                            <tr class="select">
                                <td><xsl:value-of select="cv:id"/></td>
                                <td><xsl:value-of select="cv:identite/cv:genre"/></td>
                                <td><xsl:value-of select="cv:identite/cv:nom"/></td>
                                <td><xsl:value-of select="cv:identite/cv:prenom"/></td>
                                <td><xsl:value-of select="cv:objectif"/></td>
                                <td><xsl:value-of select="cv:objectif/@statut"/></td>
                                <td><xsl:for-each select="cv:diplome/cv:titre"><xsl:value-of select="." /><xsl:if test="position() != last()">, </xsl:if></xsl:for-each></td>
                                <td class="action-column">
                                    <button class="details-button" onclick="window.location.href='html?id={cv:id}'">Détails</button>
                                    <button class="delete-button" onclick="deleteCv({cv:id})">
                                        <xsl:attribute name="onclick">
                                            <xsl:text>deleteCv(</xsl:text>
                                            <xsl:value-of select="cv:id"/>
                                            <xsl:text>)</xsl:text>
                                        </xsl:attribute>
                                        Supprimer
                                    </button>
                                </td>
                            </tr>
                        </xsl:for-each>
                    </tbody>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
