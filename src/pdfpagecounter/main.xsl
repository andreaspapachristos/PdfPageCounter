<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : main.xsl
    Created on : November 15, 2020, 9:29 AM
    Author     : master
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/">
        <html>
            
            <body>
                <table border="1" style="border-collapse:collapse">
                    <tr bgcolor="#B0C4DE" style="font-family:sans-serif">
                      <th style="text-align:left">A/A</th>
                      <th style="text-align:left">Path</th>
                      <th style="text-align:left">File</th>
                      <th style="text-align:left">Pages</th>
                    </tr>
                    <xsl:for-each select="files/file">
                    <tr>
                        <td> <xsl:number format="1 "/></td>  
                      <td> <xsl:value-of select="path" /> </td>
                      <td> <xsl:value-of select="name" /> </td>
                      <td style="text-align:right"> <xsl:value-of select="pages"/> </td>
                    </tr>  
                    </xsl:for-each>                
                 </table>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>
