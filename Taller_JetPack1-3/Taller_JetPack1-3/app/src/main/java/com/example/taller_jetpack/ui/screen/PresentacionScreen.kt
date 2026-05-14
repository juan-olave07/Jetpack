package com.example.taller_jetpack.ui.screen

// Importaciones necesarias para usar componentes de Jetpack Compose
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.taller_jetpack.R
import com.example.taller_jetpack.ui.theme.Taller_JetPackTheme

// Funcion principal que muestra todos los componentes en pantalla
@Composable
fun PresentacionScreen() {

    // Column organiza los elementos verticalmente
    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        // Llama al composable Greeting
        Greeting(nombre = "Juan Pablo")

        // Llama al composable TarjetaIA
        TarjetaIA(nombre = "Juan Pablo")

        // Llama al composable Presentacion
        Presentacion(nombre = "Juan Pablo")
    }
}

@Composable // Codigo base
fun Greeting(nombre: String, modifier: Modifier = Modifier) {

    // Surface crea una tarjeta o contenedor visual
    Surface(
        modifier = modifier
            .fillMaxWidth() // Ocupa todo el ancho disponible
            .padding(16.dp), // Margen externo
        color = MaterialTheme.colorScheme.primaryContainer, // Color de fondo
        shape = RoundedCornerShape(12.dp) // Bordes redondeados
    ) {

        // Column organiza los textos verticalmente
        Column(modifier = Modifier.padding(16.dp)) {

            // Texto de saludo
            Text(
                text = "Hola, $nombre!",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )

            // Espacio entre textos
            Spacer(modifier = Modifier.height(8.dp))

            // Texto secundario
            Text(
                text = "Bienvenido a Jetpack Compose",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable // Codigo generado por IA
fun TarjetaIA(nombre: String, modifier: Modifier = Modifier) {

    // Variables de colores personalizados
    val mediumBlue = Color(0xFF1B263B)
    val accentBlue = Color(0xFF415A77)

    // Surface utilizada como contenedor principal
    Surface(
        modifier = Modifier
            .fillMaxWidth() // Ocupa todo el ancho
            .padding(16.dp), // Margen externo
        color = mediumBlue, // Color de fondo
        shape = RoundedCornerShape(16.dp), // Bordes redondeados
        shadowElevation = 6.dp // Sombra de elevación
    ) {

        // Row organiza elementos horizontalmente
        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // Foto circular
            Image(

                // Carga la imagen desde drawable
                painter = painterResource(id = R.drawable.profile), // tu imagen aquí

                // Descripción para accesibilidad
                contentDescription = "Foto de perfil",

                // Tamaño y forma de la imagen
                modifier = Modifier
                    .size(72.dp)
                    .clip(CircleShape), // Hace la imagen circular

                // Ajusta la imagen al contenedor
                contentScale = ContentScale.Crop
            )

            // Espacio horizontal entre elementos
            Spacer(modifier = Modifier.width(16.dp))

            // Nombre y cargo
            Column(

                // Hace que la columna ocupe el espacio restante
                modifier = Modifier.weight(1f)
            ) {

                // Texto del nombre
                Text(
                    text = nombre,
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium
                )

                // Texto del cargo
                Text(
                    text = "Android Developer",
                    color = Color.LightGray,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            // Botón de contacto
            Button(

                // Acción que se ejecutará al presionar el botón
                onClick = { /* acción */ },

                // Personalización de colores del botón
                colors = ButtonDefaults.buttonColors(
                    containerColor = accentBlue,
                    contentColor = Color.White
                )
            ) {

                // Texto dentro del botón
                Text("Contactar")
            }
        }
    }
}

@Composable // Codigo adaptado
fun Presentacion(nombre: String, modifier: Modifier = Modifier) {

    // Surface usada como tarjeta principal
    Surface(
        modifier = modifier
            .fillMaxWidth() // Ocupa todo el ancho
            .padding(16.dp), // Margen externo
        color = MaterialTheme.colorScheme.surface, // Color de fondo
        shape = RoundedCornerShape(12.dp), // Bordes redondeados
        shadowElevation = 6.dp // Sombra
    ) {

        // Row organiza los elementos horizontalmente
        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // Foto circular
            Image(

                // Imagen cargada desde drawable
                painter = painterResource(id = R.drawable.profile),

                // Descripción de accesibilidad
                contentDescription = "Foto de perfil",

                // Tamaño y forma circular
                modifier = Modifier
                    .size(72.dp)
                    .clip(CircleShape),

                // Ajusta la imagen
                contentScale = ContentScale.Crop
            )

            // Espacio horizontal entre imagen y texto
            Spacer(modifier = Modifier.width(16.dp))

            // Nombre y descripcion
            Column(

                // Ocupa el espacio restante
                modifier = Modifier.weight(1f)
            ) {

                // Texto del nombre
                Text(
                    text = nombre,
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.titleMedium
                )

                // Texto de la profesión
                Text(
                    text = "Ingeniero en Sistemas y Telecomunicaciones",
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                    style = MaterialTheme.typography.bodyMedium
                )

                // Espacio entre descripción e iconos
                Spacer(modifier = Modifier.height(12.dp))

                // Row para organizar los iconos horizontalmente
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    // Icono de Instagram
                    Icon(
                        painter = painterResource(R.drawable.instagram),
                        contentDescription = "Instagram",
                        modifier = Modifier.size(28.dp),
                        tint = Color.Unspecified // Mantiene el color original
                    )

                    // Icono de Facebook
                    Icon(
                        painter = painterResource(R.drawable.facebook),
                        contentDescription = "Facebook",
                        modifier = Modifier.size(28.dp),
                        tint = Color.Unspecified
                    )

                    // Icono de Twitter
                    Icon(
                        painter = painterResource(R.drawable.twitter),
                        contentDescription = "Twitter",
                        modifier = Modifier.size(28.dp),
                        tint = Color.Unspecified
                    )
                }
            }
        }
    }
}

// Vista previa del composable Greeting
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {

    // Aplica el tema de la aplicación
    Taller_JetPackTheme {

        // Muestra la vista previa
        Greeting("Juan Pablo")
    }
}

// Vista previa del composable TarjetaIA
@Preview(showBackground = true)
@Composable
fun TarjetaIAPreview() {

    // Aplica el tema de la aplicación
    Taller_JetPackTheme {

        // Muestra la vista previa
        TarjetaIA("Juan Pablo")
    }
}

// Vista previa del composable Presentacion
@Preview(showBackground = true)
@Composable
fun PresentacionPreview() {

    // Aplica el tema de la aplicación
    Taller_JetPackTheme {

        // Muestra la vista previa
        Presentacion("Juan Pablo")
    }
}