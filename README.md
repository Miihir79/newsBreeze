![Cover](https://user-images.githubusercontent.com/66465511/193518237-74a0fe4d-e38c-4e99-9141-0e47efc8e81a.png)
# *NewsBreeze*
<p>
<img src="https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white"/>  
<img src="https://img.shields.io/badge/Kotlin-0095D5?&style=for-the-badge&logo=kotlin&logoColor=white"/>

[![NewsBreeze](https://img.shields.io/badge/NewsBreeze✅-APK-red.svg?style=for-the-badge&logo=android)](https://github.com/Miihir79/newsBreeze/releases/tag/1.0.0)

</p>

*NewsBreeze* brings to you the latest breaking news from the <a href="https://newsapi.org/">NewsApi</a>.
It has features like:
- Search for breaking news headlines
- Read news in app
- Save news


### Screenshots
<table>
  <tr>
    <td>Landing Page top news <img src="https://user-images.githubusercontent.com/66465511/193511320-2ccef969-2dbf-4b2d-92d8-2aca56301b00.jpg" width="250">
    <td>Read news <img src="https://user-images.githubusercontent.com/66465511/193511324-5d3c2a56-a643-4b80-b30a-44de7b4316ac.jpg" width="250">
    <td>Saved <img src="https://user-images.githubusercontent.com/66465511/193511326-8fff082e-9676-45f0-8253-2033d03cba5c.jpg" width="250">
  <tr>
    <td>Search in top news <img src="https://user-images.githubusercontent.com/66465511/193511329-b5f6b7d4-4bde-4ef5-af5b-867ae9cf42ed.jpg" width="250">
    <td>Search in Saved <img src="https://user-images.githubusercontent.com/66465511/193511332-a841a267-839d-4fd5-a02a-be29573fe338.jpg" width="250">
    <td>No connection error <img src="https://user-images.githubusercontent.com/66465511/193512129-ad9e2866-343a-4e20-9fb7-33cc9bc9c5ff.jpg" width="250">
</table>

### Demo

https://user-images.githubusercontent.com/66465511/193512512-80ffbb9b-4103-41fe-87aa-1ccf26775fc3.mp4


### ***Download the app from here👇***
[![NewsBreeze](https://img.shields.io/badge/NewsBreeze✅-APK-red.svg?style=for-the-badge&logo=android)](https://github.com/Miihir79/newsBreeze/releases/tag/1.0.0)


- It follows latest android practices like MVVM and LiveData.

- Have implemented Network checking feature which alearts user when network connection is lost.

- Have implemented R8 for reduced app size.

#### R8 optimizations result
  <table>
  <tr>
    <td>Before <br> <img src="https://user-images.githubusercontent.com/66465511/193511189-494c5928-03da-42c8-ac97-aea5eeb62ebe.jpg" width="250">
    <td>After <br> <img src="https://user-images.githubusercontent.com/66465511/193511030-07edf818-a268-4a08-8ade-b0c1776dae30.jpg" width="250">
 </table>

### MAD Scorecard
awaiting

## Package Structure
    
    com.mihir.newsbreeze             # Root Package
    .
    ├── data                # For data handling.
    |   ├── local           # Dao, Database
    |   ├── remote          # Remote datasources and API Service
    │   ├── model           # Model data classes
    │   └── repo            # Single source of data
    |
    ├── viewmodel           # For ViewModels
    |
    ├── ui                  # UI/View layer
    |   ├── adapters        # Adapter, ViewHolder 
    |   └── screens         # All App Screens 
    |
    └── helper              # Utility Classes
    

## How to contribute?
### What do you need to get started?
#### Latest version of android studio and basic android and googling skills will get you going.
All contributions are welcomed, Properly describe changes made and attach supporting ScreenShots in the PR. For major changes first open an issue.

## Author
Initial work: <a href="https://github.com/Miihir79">***Mihir Shah***</a> <br>
