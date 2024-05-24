<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
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
                        <xsl:for-each select="cvs/cv">
                            <tr class="select">
                                <td><xsl:value-of select="id"/></td>
                                <td><xsl:value-of select="identite/genre"/></td>
                                <td><xsl:value-of select="identite/nom"/></td>
                                <td><xsl:value-of select="identite/prenom"/></td>
                                <td><xsl:value-of select="objectif"/></td>
                                <td><xsl:value-of select="objectif/@statut"/></td>
                                <td><xsl:value-of select="diplome/titre"/></td>
                                <td class="action-column">
                                    <button class="details-button" onclick="window.location.href='html?id={id}'">Détails</button>
                                    <button class="delete-button" onclick="deleteCv({id})">
                                        <xsl:attribute name="onclick">
                                            <xsl:text>deleteCv(</xsl:text>
                                            <xsl:value-of select="id"/>
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
