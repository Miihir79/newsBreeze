# NewsBreeze

NewsBreeze brings to you the latest breaking news from the <a href="https://newsapi.org/">NewsApi</a>.
It has features like:
- Search for breaking news headlines
- Read news in app
- Save news

It follows latest android practices like MVVM and LiveData.

Have implemented Network checking feature which alearts user when network connection is lost.

Have implemented R8 for reduced app size.


### MAD Scorecard

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
