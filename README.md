# **Taller - Parcial 2**

Repositorio académico que reúne distintos talleres y proyectos desarrollados en **Android Studio** utilizando **Kotlin** y **Jetpack Compose**. Cada uno de los proyectos incluidos en este repositorio fue realizado con el propósito de fortalecer conocimientos relacionados con el desarrollo móvil moderno, aplicando herramientas y metodologías utilizadas actualmente dentro del ecosistema Android.

A lo largo de los talleres se trabajaron temas fundamentales e intermedios como la construcción de interfaces declarativas, manejo reactivo del estado, navegación entre pantallas, consumo de servicios REST, persistencia local de información y organización de proyectos mediante arquitecturas modernas como MVVM y Clean Architecture.

Además de enfocarse únicamente en la parte funcional de las aplicaciones, varios de los ejercicios también tuvieron como objetivo mejorar aspectos relacionados con la experiencia de usuario, la organización del código y la reutilización de componentes, buscando desarrollar aplicaciones más escalables, mantenibles y cercanas a entornos reales de desarrollo.

---

# Descripción General  

Este repositorio contiene una recopilación de talleres académicos desarrollados durante el módulo de Programación Móvil. Cada taller aborda una temática específica y permite evidenciar una evolución progresiva en el aprendizaje de tecnologías modernas para Android.

Los primeros proyectos estuvieron orientados a comprender la lógica básica de Jetpack Compose y la construcción de interfaces mediante el enfoque declarativo. Posteriormente, se comenzaron a implementar conceptos relacionados con manejo de estado, navegación, arquitectura MVVM, consumo de APIs y persistencia local utilizando herramientas recomendadas oficialmente por Google.

La intención principal de estos talleres fue no solamente desarrollar aplicaciones funcionales, sino también comprender cómo estructurar proyectos Android de manera más organizada, separando responsabilidades y aplicando buenas prácticas que faciliten el mantenimiento y crecimiento futuro de las aplicaciones.

Dentro del desarrollo de los proyectos también se utilizaron herramientas modernas que actualmente son ampliamente implementadas en aplicaciones Android profesionales, permitiendo adquirir experiencia práctica con tecnologías relevantes para el desarrollo móvil actual.

Entre los principales conceptos y tecnologías trabajadas se encuentran:

- Desarrollo de interfaces declarativas con Jetpack Compose
- Manejo reactivo de estados
- Arquitectura MVVM
- Navigation Compose
- Consumo de APIs REST
- Persistencia local con Room Database
- Inyección de dependencias con Hilt
- Programación asíncrona con Coroutines
- Clean Architecture
- Material Design 3
- Organización modular del código

---

# Tecnologías y Herramientas Utilizadas

Durante el desarrollo de los talleres se utilizaron diferentes herramientas y librerías modernas del ecosistema Android, permitiendo construir aplicaciones más organizadas, eficientes y mantenibles.

Las principales tecnologías implementadas fueron:

- **Kotlin**
- **Jetpack Compose**
- **Material Design 3**
- **Navigation Compose**
- **ViewModel**
- **StateFlow**
- **Retrofit**
- **Room Database**
- **Hilt**
- **Kotlin Coroutines**
- **Clean Architecture**
- **Android Studio**

El uso de estas herramientas permitió comprender cómo se desarrolla actualmente una aplicación Android moderna, utilizando componentes oficiales y arquitecturas recomendadas por Google para mejorar la escalabilidad y mantenibilidad de los proyectos.

---

# Estructura del Repositorio

## 📁 Taller_JetPack1-3
## Introducción a Jetpack Compose

Estos primeros talleres estuvieron enfocados en comprender los fundamentos básicos de Jetpack Compose y el nuevo enfoque declarativo utilizado para el desarrollo de interfaces Android modernas.

Durante el desarrollo de estas prácticas se trabajó principalmente en la construcción de interfaces sencillas utilizando componentes básicos como textos, filas, columnas y contenedores. Además, se realizaron ejercicios relacionados con personalización visual, organización de layouts y reutilización de componentes UI.

Uno de los principales objetivos de estos talleres fue comprender la diferencia entre el enfoque tradicional basado en XML y el nuevo modelo declarativo que propone Compose, permitiendo construir interfaces de manera más dinámica, flexible y sencilla de mantener.

También se exploró el uso de `Modifier`, manejo básico de estilos y previsualización de componentes mediante `@Preview`, facilitando el proceso de diseño y desarrollo de interfaces.

### Temas desarrollados

- Composables básicos
- Uso de `Text`, `Column`, `Row` y `Box`
- Manejo de `Modifier`
- Personalización de componentes
- Uso de `@Preview`
- Diseño declarativo
- Organización de interfaces
- Reutilización de componentes

### Tecnologías utilizadas

- Kotlin
- Jetpack Compose
- Material Design 3

---

## 📁 Taller_JetPack4
## Estado y Navegación en Compose

Este taller estuvo enfocado en comprender cómo manejar estados dentro de aplicaciones desarrolladas con Jetpack Compose, además de implementar navegación entre múltiples pantallas utilizando herramientas modernas del ecosistema Android.

Durante el desarrollo se realizaron interfaces dinámicas que reaccionaban automáticamente a los cambios de información, permitiendo comprender cómo Compose actualiza la UI de manera reactiva dependiendo del estado de la aplicación.

También se trabajó en formularios interactivos, validaciones básicas y renderizado de listas dinámicas mediante `LazyColumn`, facilitando la comprensión de cómo manejar grandes cantidades de información de forma eficiente.

Por otra parte, se implementó navegación entre diferentes vistas utilizando Navigation Compose, organizando las rutas de navegación y separando correctamente cada pantalla de la aplicación.

Este taller permitió entender mejor la importancia del manejo adecuado del estado y cómo este influye directamente en el comportamiento de las interfaces modernas.

### Funcionalidades implementadas

- Manejo de estado reactivo
- Formularios interactivos
- Validaciones básicas
- Navegación entre vistas
- Renderizado dinámico de listas
- Actualización automática de la interfaz

### Temas desarrollados

- `remember`
- `mutableStateOf`
- `LazyColumn`
- `NavHost`
- `Composable Routes`
- State Hoisting
- Navegación entre pantallas

### Tecnologías utilizadas

- Navigation Compose
- State Management
- Material Design 3

---

## 📁 Taller_JetPack5
## Arquitectura MVVM y Consumo de APIs

Proyecto enfocado en implementar una arquitectura más organizada y escalable utilizando el patrón MVVM (Model - View - ViewModel), permitiendo separar correctamente la lógica de negocio de la interfaz gráfica.

En este taller se integró el consumo de APIs REST mediante Retrofit y Kotlin Coroutines, permitiendo obtener información desde servicios externos y mostrarla dinámicamente dentro de la aplicación.

Uno de los aspectos más importantes trabajados en este proyecto fue el manejo de estados relacionados con procesos asíncronos, implementando estados de carga, éxito y error para mejorar el comportamiento de la interfaz frente a distintas situaciones.

También se utilizó `StateFlow` para gestionar datos reactivos y actualizar automáticamente la UI cuando existieran cambios en la información recibida desde la API.

Además de la parte funcional, este taller permitió comprender mejor la importancia de estructurar proyectos Android mediante arquitecturas limpias y organizadas, facilitando el mantenimiento del código y la escalabilidad de futuras funcionalidades.

### Características principales

- Consumo de APIs externas
- Manejo de estados de carga
- Separación de responsabilidades
- Actualización reactiva de la interfaz
- Programación asíncrona con corrutinas
- Organización de capas mediante MVVM

### Temas desarrollados

- Arquitectura MVVM
- `ViewModel`
- `StateFlow`
- Retrofit
- Coroutines
- Manejo de errores
- Estados `Loading`, `Success` y `Error`

### Tecnologías utilizadas

- Retrofit
- Kotlin Coroutines
- ViewModel
- StateFlow
- Hilt

---

## 📁 Taller_Noticias
## Aplicación de Noticias

Aplicación Android desarrollada utilizando principios de arquitectura limpia y herramientas modernas del ecosistema Android, enfocada en el consumo y visualización de noticias en tiempo real mediante APIs REST.

El proyecto permite consultar noticias dinámicamente desde servicios externos y mostrarlas dentro de una interfaz moderna desarrollada completamente con Jetpack Compose.

Durante el desarrollo también se implementó persistencia local utilizando Room Database, permitiendo almacenar información dentro del dispositivo y mejorar la experiencia del usuario.

Además, se trabajó en la navegación entre pantallas, manejo de estados, separación de capas y actualización dinámica de información, buscando desarrollar una aplicación más organizada y cercana a un entorno real de desarrollo Android.

Uno de los principales objetivos de este proyecto fue aplicar de manera práctica varios de los conceptos trabajados en talleres anteriores, integrando arquitectura MVVM, consumo de APIs, persistencia local e inyección de dependencias dentro de un mismo proyecto.

### Funcionalidades principales:

- Visualización de noticias en tiempo real
- Navegación entre categorías y detalles
- Persistencia local de información
- Manejo de estados y carga dinámica
- Interfaz moderna y adaptable
- Actualización dinámica de contenido

### Características implementadas:

- Arquitectura MVVM  
- Clean Architecture  
- Room Database  
- Retrofit  
- Inyección de dependencias con Hilt  
- Navegación entre pantallas  
- Material Design 3  
- Persistencia local  
- Consumo de APIs REST  

### Tecnologías utilizadas:

- Jetpack Compose
- Room Database
- Retrofit
- Hilt
- Kotlin Coroutines
- Material Design 3

---

## Objetivos de Aprendizaje ##

El desarrollo de estos talleres permitió fortalecer habilidades relacionadas con el desarrollo Android moderno, especialmente en temas de arquitectura, diseño de interfaces, consumo de servicios web y manejo de datos.

Además de adquirir conocimientos técnicos, los proyectos también ayudaron a comprender la importancia de mantener una buena organización del código, reutilizar componentes y aplicar buenas prácticas de programación para construir aplicaciones más limpias y mantenibles.

Cada taller aportó conocimientos progresivos que permitieron comprender mejor cómo se desarrolla actualmente una aplicación Android moderna utilizando herramientas oficiales y arquitecturas recomendadas por Google.

---

## Requisitos para la Ejecución ##

Para ejecutar correctamente cualquiera de los proyectos incluidos en este repositorio se recomienda contar con un entorno de desarrollo Android correctamente configurado.

Requisitos recomendados:

- Android Studio actualizado
- JDK 17 o superior
- SDK de Android configurado
- Gradle compatible
- Conexión a internet para consumo de APIs
- Emulador Android o dispositivo físico
