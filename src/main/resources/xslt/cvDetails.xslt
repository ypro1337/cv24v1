
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <head>
                <title>CV Details</title>
                <link rel="stylesheet" type="text/css" href="/styles/styledetails.css"/>
            </head>
            <body>
                <h1>CV Details</h1>
                <table>
                    <tr>
                        <th colspan="2" class="section-header">Identité</th>
                    </tr>
                    <tr>
                        <th>ID</th>
                        <td><xsl:value-of select="cv/id"/></td>
                    </tr>
                    <tr>
                        <th>Genre</th>
                        <td><xsl:value-of select="cv/identite/genre"/></td>
                    </tr>
                    <tr>
                        <th>Nom</th>
                        <td><xsl:value-of select="cv/identite/nom"/></td>
                    </tr>
                    <tr>
                        <th>Prénom</th>
                        <td><xsl:value-of select="cv/identite/prenom"/></td>
                    </tr>
                    <tr>
                        <th>Téléphone</th>
                        <td><xsl:value-of select="cv/identite/tel"/></td>
                    </tr>
                    <tr>
                        <th>Email</th>
                        <td><xsl:value-of select="cv/identite/mel"/></td>
                    </tr>

                    <tr>
                        <th colspan="2" class="section-header">Objectif</th>
                    </tr>
                    <tr>
                        <th>Objectif</th>
                        <td><xsl:value-of select="cv/objectif/value"/></td>
                    </tr>
                    <tr>
                        <th>Statut</th>
                        <td><xsl:value-of select="cv/objectif/statut"/></td>
                    </tr>

                    <tr>
                        <th colspan="2" class="section-header">Prof</th>
                    </tr>
                    <xsl:for-each select="cv/prof/details/detail">
                        <tr>
                            <th>Date Début</th>
                            <td><xsl:value-of select="datedeb"/></td>
                        </tr>
                        <tr>
                            <th>Date Fin</th>
                            <td><xsl:value-of select="datefin"/></td>
                        </tr>
                        <tr>
                            <th>Titre</th>
                            <td><xsl:value-of select="titre"/></td>
                        </tr>
                    </xsl:for-each>

                    <tr>
                        <th colspan="2" class="section-header">Compétences - Diplômes</th>
                    </tr>
                    <xsl:for-each select="cv/competences/diplomes/diplome">
                        <tr>
                            <th>Date</th>
                            <td><xsl:value-of select="date"/></td>
                        </tr>
                        <tr>
                            <th>Institut</th>
                            <td><xsl:value-of select="institut"/></td>
                        </tr>
                        <tr>
                            <th>Titre</th>
                            <td><xsl:for-each select="titres/titre"><xsl:value-of select="."/><xsl:if test="position() != last()">, </xsl:if></xsl:for-each></td>
                        </tr>
                        <tr>
                            <th>Niveau</th>
                            <td><xsl:value-of select="@niveau"/></td>
                        </tr>
                    </xsl:for-each>

                    <tr>
                        <th colspan="2" class="section-header">Compétences - Certifications</th>
                    </tr>
                    <xsl:for-each select="cv/competences/certifs/certif">
                        <tr>
                            <th>Date Début</th>
                            <td><xsl:value-of select="datedeb"/></td>
                        </tr>
                        <tr>
                            <th>Date Fin</th>
                            <td><xsl:value-of select="datefin"/></td>
                        </tr>
                        <tr>
                            <th>Titre</th>
                            <td><xsl:value-of select="titre"/></td>
                        </tr>
                    </xsl:for-each>

                    <tr>
                        <th colspan="2" class="section-header">Divers - Langues Vivantes</th>
                    </tr>
                    <xsl:for-each select="cv/divers/lv/lv">
                        <tr>
                            <th>Langue</th>
                            <td><xsl:value-of select="@lang"/></td>
                        </tr>
                        <tr>
                            <th>Certificat</th>
                            <td><xsl:value-of select="@cert"/></td>
                        </tr>
                        <tr>
                            <th>Niveau S</th>
                            <td><xsl:value-of select="@nivs"/></td>
                        </tr>
                        <tr>
                            <th>Niveau I</th>
                            <td><xsl:value-of select="@nivi"/></td>
                        </tr>
                    </xsl:for-each>

                    <tr>
                        <th colspan="2" class="section-header">Divers - Autres</th>
                    </tr>
                    <xsl:for-each select="cv/divers/autre">
                        <tr>
                            <th>Titre</th>
                            <td><xsl:value-of select="@titre"/></td>
                        </tr>
                        <tr>
                            <th>Commentaire</th>
                            <td><xsl:value-of select="@comment"/></td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
