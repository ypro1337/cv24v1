<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <head>
                <title>CV Resume</title>
                <style>
                    table {
                    width: 100%;
                    border-collapse: collapse;
                    }
                    th, td {
                    padding: 8px;
                    text-align: left;
                    border-bottom: 1px solid #ddd;
                    }
                    th {
                    background-color: #f2f2f2;
                    }
                    button {
                    padding: 5px 10px;
                    margin-right: 5px;
                    background-color: #4CAF50; /* Green */
                    color: white;
                    border: none;
                    border-radius: 5px;
                    cursor: pointer;
                    }
                    button:hover {
                    background-color: #45a049;
                    }
                </style>
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
                            <tr>
                                <td><xsl:value-of select="id"/></td>
                                <td><xsl:value-of select="identite/genre"/></td>
                                <td><xsl:value-of select="identite/nom"/></td>
                                <td><xsl:value-of select="identite/prenom"/></td>
                                <td><xsl:value-of select="objectif"/></td>
                                <td><xsl:value-of select="objectif/@statut"/></td>
                                <td><xsl:value-of select="diplome/titre"/></td>
                                <td>
                                    <button onclick="window.location.href='html?id={id}'">Détails</button>
                                    <button onclick="confirm('Êtes-vous sûr de vouloir supprimer ce CV?') &amp;&amp; window.location.href='cv24/delete?id={id}'">Supprimer</button>
                                </td>
                            </tr>
                        </xsl:for-each>
                    </tbody>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
