## Σας καλωσορίζω στο πρώτο μου utillity 

Πρόκειται για ένα project το οποίο έφτιαξα τις ημέρες της μερικής απαγόρευσης κυκλοφορίας λόγω της πανδημίας. Μετά από αρκετά χρόνια ασχολήθηκα με την αγαπήμενη java την οποία και είχα "παρατήσει" μετά το τέλος του πανεπιστημίου μιας και οι σιερήνες της python με "τράβηξαν"προς τα εκεί.
Είναι ένα utillity το οποίο και κάνει ευρία χρήση της swing βιβλιοθήκης, ενώ η λειτουργικότητα τoy περιορίζεται στην απαρήθμιση των  pdf αρχείων που βρίσκονται σε δοθέντα από τον χρήστη κατάλογο (directory), καθώw και τον αριθμό των σελιδων του.


### Λίγα λόγια για τον κώδικα

Ο κώδικας είναι γραμμένος στο ide Netbeans-12.1 και τα runtime depedencies περιορίζονται στο jre8 (ή νεότερο φυσικά), αλλά στους περισσότερους χρήστες τρέχει η 8η ή 10η έκδοση. Το αρχείο που παράγει είναι ένα html το οποίο προκύπτει από την μετατροπή ενός xml με χρήση xsl φύλλου.
Μέχρι την ώρα που γράφονται αυτές οι γραμμές έχω ανεβάσει ένα tar το οποίο τρέχει απροβλημάτιστα σε linux os. Φυσικά οι γνώστες μπορούν να αποσπάσουν το jar αρχείο και να το τρέξουν και σε windows.
Παρακάτω παράθετω τις απαραίτητες αλλαγές που πρέπει να γίνουν στο buil.xml του ant προκειμένου να μεταγλωτήσει τον κωδίκα με όλες τις βιβλιοθήκες από τις οποίες εξαρτάται (αν και το αρχείο το έχω και στον κώδικα). Βέβαια η απαίτηση αυτή (της ενσωμάτωσης των βιβλιοθηκών στο jar) ανεβάζει το μέγεθος του στα 15 MegaBytes. Επίσης λόγω της ανδρομικής ανίχνευσης των αρχείων πιθανόν σε μεγάλους καταλόγους να χρειαστεί σημαντική επεξεργαστική ισχύς.

```markdown
<!--
Custom code for including all dependencies (*.jar) 
-->
<target name="package-for-store" depends="jar">
<property name="store.jar.name" value="pdfpagecounter"/>
<property name="store.dir" value="store"/>
<property name="store.jar" value="${store.dir}/${store.jar.name}.jar"/>
<echo message="Packaging ${application.title} into a single JAR at ${store.jar}"/>
<delete dir="${store.dir}"/>
<mkdir dir="${store.dir}"/>
<jar destfile="${store.dir}/temp_final.jar" filesetmanifest="skip">
<zipgroupfileset dir="dist" includes="*.jar"/>
<zipgroupfileset dir="dist/lib" includes="*.jar"/>
<manifest>
<attribute name="Main-Class" value="${main.class}"/>
</manifest>
</jar>
<zip destfile="${store.jar}">
<zipfileset src="${store.dir}/temp_final.jar" excludes="META-INF/*.SF, META-INF/*.DSA, META-INF/*.RSA"/>
</zip>
<delete file="${store.dir}/temp_final.jar"/>
</target>
<!--End of custon code -->
```



### Το project συνεχίζεται

Κάθε παρατήρηση ή διόρθωση είναι ευπρόσδεκτη

### Support or Contact

email επικοινωνίας admin@linuxdide.gr
