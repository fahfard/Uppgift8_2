# uppgift8

> Uppgift 8, deadline 8.12 kl. 23:55
>Information för övningen:
Starta Eclipse och skapa ett nytt Javaprojekt.
Skapa ett nytt Javaprojekt med titeln Uppg8. Alltså, infoga inte ditt namn. I detta projekt skall du placera alla klasser som hör till dina lösningar. Namnge huvudklasserna enligt mallen Uppg8«Moment #». Använd denna mall i alla inlämningsuppgifter i fortsättningen.
> OBS! Du bör ha löst uppgifter till minst 5 poäng. 
> OBS2! Poäng ges endast för exekverbara filer. Okompilerbara beaktas inte.
OBS3! För att underlätta vårt arbete, vänligen dubbelchecka att din uploadade .zip-fil kan importeras.
> Motivering: Ett lite "större" program med övning angående objekt och praktisk anknytning innehållande enkel fil-i/o och generiska typer. Tanken är också att ni skall lära er hur viktigt definition av en bra API (Application programming interface) är -> ta gärna en diskussion om detta på forumet.

> Uppgift
> Moment 1 (10p + bonus 8p): Skriv ett produktionsplaneringsprogram! Alltså, tänk dig att du gör ett program för ett företag inom Lego-branschen. 

Programmet skall kunna vid emottagen beställning lägga respektive produkter i produktion vilka i sin tur reserverar bitar från lagret och allokerar (bokar tid för) en arbetare att arbeta på detta. För att få en gemensam struktrur låter vi lagret bestå enbart av legobitar och industrin opererar alltså enbart inom Lego-branschen. Informationen skall lagras i en fil.

> Du skall skapa en klass Lager [x] som innehåller (endast fyrkantiga) legobitar av olika storlek (alla en multipel av 3.2mm * 8mm (en 1*1 platt bit) http://en.wikipedia.org/wiki/Lego ). Alltså, ditt lager skall innehålla en bit EttXTva (1x2 röda biten på http://en.wikipedia.org/wiki/Lego#mediaviewer/File:Lego_dimensions.svg ) och TreXEtt (3X1 gula biten http://en.wikipedia.org/wiki/Lego#mediaviewer/File:Lego_dimensions.svg ) och vad som helst för andra bitar med samma struktur. Vi beaktar inte färg och inte heller "specialbitar".

> Din uppgift är att skapa legoprodukter av typ Produkt, alltså en klass Produkt. En produkt (en legomodell, t.ex. en polisstation) består av en komposition av (i vårt fall fyrkantiga enfärgade) bitar. 

En produkt är en enhet som säljes. Alltså, vid en beställning av en produkt (säg polisstation) ska dess angivna antal legobitar reserveras från lagret. En Produkt innehåller även data för nettopris, arbetspris (klockat antal tidsenheter, säg minuter i detta fall * pris per minut vilket får vara fixt, säg 0.5 euro / min). Nettopriset fås från klassen Lager, där varje bit (objekt av typ Lager) skall ha ett saldo, inköpspris, reserverade bitar och en trigger (beställningspunkt). Trigger är ett gränsvärde (t.ex. 5st) som gör att ett alarm genereras, i detta läge ett informaitonsfönster som meddelar detta. 

En klass Arbetare definierar varje arbetare i produktionen. En arbetare arbetar alltså 7*60 min per dag och kan tilldelas arbetsuppgifter enligt detta. På så sätt kan fabrikens försäljare beräkna när den sålda produkten kan förväntas levereras (frånsett sjukskrivningar och semestrar).

Med detta skall programmet kunna ta emot - via ett grafiskt användargränssnitt - en ny beställning, ange lagersituationen i form av delar och deras värde (i euro) samt lagrets totalvärde, ett inköp (inlägg av bitar i lagret), en ny arbetare och strykning av en existerande arbetare samt ange en ny produkt (en modell och dess komposition av olika bitar t.ex. som kommaseparerad input vars typ anges exempelvis kunde 5_1x2, <följande bit> betyda 5 st. 1x2 bitar). Gränssnittet skall även kunna ange när beställningskön tar slut (hur många dagar, timmar och minuter tills detta, kom ihåg, 7h arbete per arbetare per dag -> om du anställer (skapar) en ny arbetare, eller en gammal slutar, måsta allt beräknas om).

Vid start av programmet skall det läsa in filer för respektive klass, dvs. lager, produkt och arbetare. Bra ändelse är .txt och värden angivna separerat med komma. Initialt är dessa filer tomma, men efter exekvering skall de innehålla något. Alltså, vid avslutande av programmet skall dess tillstånd skrivas till filerna. Vad som ska skrivas är alla objekts data i respektive fil. T.ex. om Lagret består av 30st EttXTva och 20st SexXAtta bitar, skall det stå EttXTva, 30 <radbyte> SexXAtta, 20 i filen som representerar lagret. Likadant för all övrig data i objektet som du implementerat och detta för alla objekt. Idén är att du måste läsa in från en fil och spara i en fil på ett strukturerat sätt.

Givet lyckad implementation av det som anges ovan, är denna uppgift godkänd. Du anses då klara av objekt till en utsträckning som krävs av kursen. Kom ihåg att ange hur inputen skall se ut (vilka bitar du implementerat)!

>Kom ihåg att behandla felmeddelanden genomgående i detta moment och efter!!!
Modellera inte tidens framskridande!! Det gör det hela mycket enklare.

>Inlämning
Eclipse-projektet innehållande lösningarna skickas in som ett zip-paket via Moodle:

>Högerklicka på projektet, välj "Export" i menyn.
Markera General -> Archive File. Klicka Next.
Verifiera att alla källkodsfiler (.java) och projektinställningnar (.classpath, .project) är inkluderade, samt att destinationsfilen har suffixet .zip. Klicka next.
Ladda upp destinationsfilen som svar på denna övning. Du kan före deadline ladda upp en ny fil (som då överskriver den tidigare inskickade filen).
