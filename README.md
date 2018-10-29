               		IDEE: FITNESS_APP
„Fitness App“ verwendet den eingebauten Sensor step counter, um die Schritte zu zählen. GPS-tracking wird nicht verwendet, daher verbraucht sie sehr wenig Akkuleistung, deshalb ist „Fitness App“ für die Personen, die sehr lange laufen wollen sehr praktisch, da man möglicherweise keine zusätzliche Powerbank mitnehmen muss. Sie rechnet außerdem die verbrannten Kalorien, Laufentfernung und die Zeit.
Es ist keine Anmeldung oder Registrierung erforderlich. Die App sammelt oder speichert zu keinem Zeitpunkt personenbezogene Daten oder teilt die Daten mit den anderen. Einerseits sind die genommenen Daten sicher, andererseits die Bedingung der App ist daher leichter. Außerdem man kann die Zählfunktion jederzeit pausieren, starten oder zurücksetzen und wieder bei 0 anfangen.
Die App wurde sehr schlicht konstruiert. Das klare Design hilft bei der einfachen Verwendung. Um die Genauigkeit der Ausgaben zu verbessern, sollte man die Schrittlänge und verbrachte Kalorien pro km in den Einstellungen eingeben. Diese werden zur Berechnung der Laufentfernung und die Kalorien verwendet.

## SENSOREN
### Einleitung
Die Entwicklung von Smartphones wird immer schneller, sie verfügen über mehr Rechenleistung, neue Technologien und immer mehr Sensoren. Smartphones stecken voller Sensoren, die ständig Daten sammeln. Entsprechende Programme können damit sehen, hören und fühlen, was alles in der Umgebung des Gerätes geschieht.
Sensoren werden für unterschiedliche Zwecke eingesetzt, der Helligkeitssensor beispielsweise passt die Displaybeleuchtung abhängig von dem Umgebungslicht automatisch an, der Annäherungssensor deaktiviert beim Telefonieren das Display, wenn man das Gerät nah ans Ohr hält, um versehentliche Eingaben durch Berührung mit der Wange zu verhindern. Bewegungssensible Sensoren wie der Accelerometer, Orientierungssensor und das Gyroskop werden zur Ausrichtung des Displays (Hoch oder Querformat), zur Steuerung von Spielen, aber vor allem zur Untergrund- und Innenraum-Navigation genutzt.
Entwicklung mobiler Anwendungen WS1718 - Ali Rahimpour
30 .
### Sensoren im Android-SDK
Die Android-Plattform unterstützt drei große Kategorien von Sensoren:
#### Bewegungssensoren
Diese Sensoren messen Beschleunigungskräfte und Rotationskräfte entlang drei Achsen. Zu dieser Kategorie gehören z.B. Beschleunigungsmesser, Schwerkraftsenso-ren, Gyroskope und Rotationsvektorsensoren.
#### Umweltsensoren
Diese Sensoren messen verschiedene Umgebungsbedingungen, wie z.B. Temperatur, Druck, Beleuchtung und Feuchtigkeit. Zu dieser Kategorie gehören z.B. Barometer, Photometer und Thermometer.
#### Positionssensoren
Diese Sensoren messen die physikalische Position eines Geräts. Zu dieser Kategorie gehören z.B. Orientierungssensoren und Magnetometer.
Man kann diese Sensoren zugreifen und mithilfe des Android Sensor-Framework Rohdaten erwerben. Sensor-Framework ist ein Teil von „android.hardware“ Package und umfasst die folgenden Klassen und Schnittstellen:
### SensorManager
Die Funktionen, damit man auf die Sensoren im Handy zugreifen kann, sind über die SensorManager-Klasse erreichbar. Diese Klasse bietet verschiedene Methoden und konstanten für den Zugriff auf die Sensoren.
#### Sensor
Man Kann diese Klasse verwenden, um eine Instanz eines bestimmten Sensors zu erstellen. (In meinem Fall: TYPE_STEP_COUNTER) Diese Klasse bietet verschiedene Methoden, mit denen man die Fähigkeiten eines Sensors bestimmen kann.
#### SensorEvent
Das System erstellt mit dieser Klasse ein "sensor event"-Objekt, das die Informationen über ein "sensor event" liefert. Ein "sensor event"-Objekt enthält die folgenden Informationen: die Rohsensordaten, den Typ des Sensors, der das Ereignis (event) generiert hat, die Genauigkeit und so weiter.
#### SensorEventListener
Man kann diese Schnittstelle verwenden, um zwei Callback-Methoden zu erstellen, die Benachrichtigungen (sensor events) empfangen, wenn sich die Sensorgenauigkeit oder die Sensorwerte ändern  				       
		   www.AliRahimpour.com			       
	 Entwicklung Mobieler Anwendungen WS17/18     	       
      							       


## Wenn Sie das Projekt-Layout nicht sehen können, Synchronisieren Sie 
  bitte das Projekt mit "Gradle-File" 

## Um Das fertige APP-Layout zu sehen, wählen Sie bitte "activity_main"-
  Datei aus dem Verzeichnis:
  app -> src -> main -> res -> layout-v24 
