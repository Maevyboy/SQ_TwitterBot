# Feedback
##Dokumentation
Die Dokumentation ist formatiert und damit angenehm zu lesen. Da das Bedienkonzept sehr einfach gehalten wurde reicht eine sehr kurze Dokumentation in diesem Fall aus.

##Git- und Github-Nutzung
Die Commits sind minimal und zeigen keinerlei Workflow. Die Commit-Messages sind hne jeglichen Wert.
Auf keinen Fall dürfen API-Keys und Secrets in ein Projekt eingecheckt werden, vor allem nicht dann, wenn dieses public erreichbar ist.

##Funktionalität
Das Programm kann einem Nutzer folgen und bei Erwähnung diesem eine direkte Nachricht schreiben. Das ist nicht genau wie gefordert, eigentlich sollte hier die Antwort mit Erwähnung des Fragestellers getwittert werden.

##Code
Der Code ist sehr übersichtlich und kurz. Das Programm daher sehr leicht zu verstehen, sehr gut.
Die Klasse start.java sollte eigentlich wie jede Java-Klasse einen großgeschriebenen Klassennamen besitzen.
In der Klasse OAutoLogin ist mir folgender WrackTrain aufgefallen:

    ConfigurationBuilder myCb = new ConfigurationBuilder();
	myCb.setDebugEnabled(true).setOAuthConsumerKey(consumerKey).setOAuthConsumerSecret(consumerSecret).setOAuthAccessToken(token).setOAuthAccessTokenSecret(secretToken);

Das empfinde ich als schlechten Stil, aber leider durch die Api fast unvermeidbar...

***
Bewertung: 4P
