## Learning Jetpack Compose 


![Screenshot 2024-08-28 124801.png](..%2F..%2F..%2F..%2F..%2FBilder%2FScreenshots%2FScreenshot%202024-08-28%20124801.png)

### First step 
We try to inject the dependences 
![img.png](img.png)

### Change the state of a variable 

Quand la valeur d'une vairable est changée , on doit également rappéler la function 

### Screen orientation change 
rememberSaveValue 

### Séparer la logique de la vue avec View -> ViewModel 
On utilise ici le View Model avec les liveData 
![img_1.png](img_1.png)

### construire une UI 

![img_2.png](img_2.png)

### The struction of the content 

![img_3.png](img_3.png)

### Using the constraint layout 
![img_4.png](img_4.png)
 Constraint Layout simplifie les la conception de l'interface en se focalisant plus sur les différents élements de l'interface . 
remplace Column, Row ou Box dans l'interface . 
# Modifier.constrainAs , start , end , top , buttom , 
### oDER USI Elements from ConstraintLayout 
![img_5.png](img_5.png)

Guidlines : Set the reference Guideline 
            Use reference guidlines to arrange Composable 
            Pass guidelines as parameters to contraintAs 
![img_6.png](img_6.png)

Barriers :   Use the most  extreme element in UI to create virtual guidlines 

       
![img_7.png](img_7.png)

Chain : Ici on utilise un groupe de layout comme reférence 
![img_8.png](img_8.png)

### use coil to load the image asynchron in the app 
d'autres bibliotheques sont Glide ou Picasso,
![img_9.png](img_9.png)

### filter , short by name , search 

![img_10.png](img_10.png)

### TopAppBar , Action , Scaffold , ButtonAppBar , FloatingAction Button 

![img_11.png](img_11.png)

### NavigationIcon , TopAppBar , ActionButtons , FixNavbar ,non  Fix Button on the screen , Buttonnavigation , ButtomBar , ButtomAppBar , floatingActionButton 

![img_12.png](img_12.png)

### Themes in Jetpack Compose 
Theme : fonts , color for cards , Typography , Color for Background Surface 
![img_13.png](img_13.png)
### Typography in jetpack compose . 


![img_14.png](img_14.png)

###  shape , type and theme 
on peut créer une classe et y développer des themes personels . 

### Navigation 
Toutes les questions intéressantes sont sur la photo 

![img_15.png](img_15.png)

Les thermes les plus importants : Navhost , NavController , NavGraph , Action , Destination 

Destination : The screens 
NavHost : Help to visit 
NavController : Is using by NavHost by the visit 
Action : Route beetween the Destionation
Navgraph : Contents all Informations and likes all the concepts 
inclusive : inclusive = false quand on veut rentrer á l'état initial 

## Utiliser le scaffold avec la navigation 
Comment utiliser la navigation et le scaffold ? 

![img_16.png](img_16.png)

### Comment passer les données entre deux composables ? 
On peux passer un string et le récupérer directement 
![img_17.png](img_17.png)

### Comment passer plutot des données objets comme du json et autres 
serialisation et deserialisation des données envoyées : Pour le faire , il faut á tous prix ajouter les dépendances et créer une classe serialisable .

### Simplification de la navigation avec le repository design pattern 
![img_18.png](img_18.png)

### Utiliser le viewModel pour partager des données entre différents écrans . 
L'objectif est de créer un viewModel , initialiser un student comme MutableLiveData . Jaque fois qu'un button est appuyé , on initialise la valeur de la variable 
- Il y'a également la possibilité de passer les données en Reopository et ViewModel 
![img_19.png](img_19.png)

### Dialog et navigation 
LA 
![img_20.png](img_20.png)
![img_21.png](img_21.png)

Cela ressemble á ceci : 
![img_22.png](img_22.png)
![img_23.png](img_23.png)

## créer un toast en Kotlin et passer des données d'un écran á un dialog 
On peux aussi naviger de la page vers un dialog 

### Retour sur les states managements 
remember et mutableStateOf , derivedStateOf , produceState , rememberUpdateState , rememberCoroutineScope , LaunchedAffect , DisposableEffect , SideEffect 

## rememberCouroutineScope 
pour lancer un couroutine et arréter la couroutine lorsque le composant est fermé 
![img_24.png](img_24.png)
## SideEffect 
trigger automatiquement en utilisant des launched Effect : CounterMessage 

### Que faire s'il y'a changement de composable 
Lorsqu'on utilise un composable enfant et papa il faut utiliser le rememberstateUpdate() 
![img_25.png](img_25.png)

### Dispoable Effect 
chaque fois que l'état change ' , il peut etre appéllé 

### sideEffect est appéllé lorsque le composable change d'état 

### produceState 
est utiliser lorsqu'une API remet des données . C'est généralement quand 