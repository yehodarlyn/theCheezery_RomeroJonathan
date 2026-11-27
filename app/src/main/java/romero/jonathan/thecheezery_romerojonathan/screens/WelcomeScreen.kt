package romero.jonathan.thecheezery_romerojonathan.screens

@Composable
fun WelcomeScreen(onGetStarted: () -> Unit){
    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxWidth().weight(0.6f), contentAlignment = Alignment.Center){
            Image(painter = painterResource(id = R.drawable.the_cheezery), contentDescription = "logo", modifier = Modifier.fillMaxSize())
        }
        Column(modifier = Modifier.fillMaxWidth().weight(0.4f).background(Purple_grey).padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Welcome to The Cheezery", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(8.dp))

            Text(text = "Home of the most wonderful desserts ever seen (and tasted) by the human being.", color = Color.White, fontSize = 14.sp, fontStyle = FontStyle.Italic)
            Spacer(Modifier.height(24.dp))

            Button(onClick = onGetStarted, colors = ButtonDefaults.buttonColors(containerColor = Pinky), shape = RoundedCornerShape(50)) {
                Text("Get started!", color = Color.White)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview(){
    welcomeScreen(onGetStarted = {})
}