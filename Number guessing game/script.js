let numberToGuess = Math.round(Math.random() * 100);
let attempts = 0;

function guessTheNumber() {
    attempts++;
    displayTries.innerHTML = "Versuche: " + attempts;

    if (numberToGuess == myNumber.value) {
         headline.innerHTML = "Du hast gewonnen!!! 🥳🎉";
         let jsConfetti = new JSConfetti();
         jsConfetti.addConfetti();
    }

    if (numberToGuess < myNumber.value) {
        headline.innerHTML = "Die Zahl ist kleiner!";
    }

    if (numberToGuess > myNumber.value) {
        headline.innerHTML = "Die Zahl ist größer!";
    }

    myNumber.value = " ";
}