<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <head>
                <title>CV Details</title>
                <style>
                    table {
                    width: 70%;
                    border-collapse: collapse;
                    margin: 20px 0;
                    }
                    th, td {
                    padding: 8px;
                    text-align: left;
                    border-bottom: 1px solid #ddd;
                    }
                    th {
                    background-color: #f2f2f2;
                    }
                </style>
            </head>
            <body>
                <h1>CV Details</h1>
                <h2>Identité</h2>
                <table>
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
                </table>

                <h2>Objectif</h2>
                <table>
                    <tr>
                        <th>Objectif</th>
                        <td><xsl:value-of select="cv/objectif/value"/></td>
                    </tr>
                    <tr>
                        <th>Statut</th>
                        <td><xsl:value-of select="cv/objectif/statut"/></td>
                    </tr>
                </table>

                <h2>Prof</h2>
                <table>
                    <thead>
                        <tr>
                            <th>Date Début</th>
                            <th>Date Fin</th>
                            <th>Titre</th>
                        </tr>
                    </thead>
                    <tbody>
                        <xsl:for-each select="cv/prof/details">
                            <tr>
                                <td><xsl:value-of select="datedeb"/></td>
                                <td><xsl:value-of select="datefin"/></td>
                                <td><xsl:value-of select="titre"/></td>
                            </tr>
                        </xsl:for-each>
                    </tbody>
                </table>

                <h2>Compétences</h2>
                <h3>Diplômes</h3>
                <table>
                    <thead>
                        <tr>
                            <th>Date</th>
                            <th>Institut</th>
                            <th>Titre</th>
                            <th>Niveau</th>
                        </tr>
                    </thead>
                    <tbody>
                        <xsl:for-each select="cv/competences/diplomes">
                            <tr>
                                <td><xsl:value-of select="date"/></td>
                                <td><xsl:value-of select="institut"/></td>
                                <td><xsl:value-of select="titres/titre"/></td>
                                <td><xsl:value-of select="niveau"/></td>
                            </tr>
                        </xsl:for-each>
                    </tbody>
                </table>

                <h3>Certifications</h3>
                <table>
                    <thead>
                        <tr>
                            <th>Date Début</th>
                            <th>Date Fin</th>
                            <th>Titre</th>
                        </tr>
                    </thead>
                    <tbody>
                        <xsl:for-each select="cv/competences/certifs">
                            <tr>
                                <td><xsl:value-of select="datedeb"/></td>
                                <td><xsl:value-of select="datefin"/></td>
                                <td><xsl:value-of select="titre"/></td>
                            </tr>
                        </xsl:for-each>
                    </tbody>
                </table>

                <h2>Divers</h2>
                <h3>Langues Vivantes</h3>
                <table>
                    <thead>
                        <tr>
                            <th>Langue</th>
                            <th>Certificat</th>
                            <th>Niveau S</th>
                            <th>Niveau I</th>
                        </tr>
                    </thead>
                    <tbody>
                        <xsl:for-each select="cv/divers/lv">
                            <tr>
                                <td><xsl:value-of select="lang"/></td>
                                <td><xsl:value-of select="cert"/></td>
                                <td><xsl:value-of select="nivs"/></td>
                                <td><xsl:value-of select="nivi"/></td>
                            </tr>
                        </xsl:for-each>
                    </tbody>
                </table>

                <h3>Autres</h3>
                <table>
                    <thead>
                        <tr>
                            <th>Titre</th>
                            <th>Commentaire</th>
                        </tr>
                    </thead>
                    <tbody>
                        <xsl:for-each select="cv/divers/autre">
                            <tr>
                                <td><xsl:value-of select="titre"/></td>
                                <td><xsl:value-of select="comment"/></td>
                            </tr>
                        </xsl:for-each>
                    </tbody>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
