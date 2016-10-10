# PolygonFX
PolygonFX est un petit bout de code écrit en JavaFX qui permet de créer des "Polygones" tout en ayant le contrôle sur les points qui les constituent.

# Aperçu de PolygonFX
![PolygonFX](https://cloud.githubusercontent.com/assets/19537533/19253905/6e2f0694-8f46-11e6-9145-ea6512cc91c8.gif)

# Fonctionement
Ce code est basé sur le format SVG, il se sert de 3 transformations (MoveTo/LineTo/ClosePath) et fonctionne comme suit :
-Touche 'D' (Draw) pour commencer un nouveau dessin ou continuer un en cours.
-Touche 'S' (Stop) pour arrêter le dessin en cours.
-Touche 'Entrer' (Valider) pour valider la forme créée.
-Evénement souris pour capturer la position du curseur est déplacer les pointeurs (Drag & Drop).
