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
            <head>
                <!-- CSS -->
                <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous"></link>

                <!-- jQuery and JS bundle w/ Popper.js -->
                <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
            </head>
            
            <body>
                <table class="table table-bordered table-dark">
                    <tr bgcolor="#B0C4DE" style="font-family:sans-serif">
                        <th scope="col">#</th>
                        <th scope="col">Path</th>
                        <th scope="col">File</th>
                        <th scope="col">Pages</th>
                    </tr>
                    <xsl:for-each select="files/file">
                        <tr scope="row">
                            <td> 
                                <xsl:number format="1 "/>
                            </td>  
                            <td> 
                                <a href="{path}">
                                <xsl:value-of select="path" />
                                </a> 
                            </td>
                            <td>
                                <a href="{concat(path,name)}"> 
                                <xsl:value-of select="name" />
                                </a> 
                            </td>
                            <td style="text-align:right"> 
                                <xsl:value-of select="pages"/> 
                            </td>
                        </tr>  
                    </xsl:for-each>                
                </table>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>
