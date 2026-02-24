let numberToGuess = Math.round(Math.random() * 100);
let attempts = 0;

function guessTheNumber() {
    attempts++;
    displayTries.innerHTML = "Attempts: " + attempts;

    if (numberToGuess == myNumber.value) {
         headline.innerHTML = "You won !!! 🥳🎉";
         let jsConfetti = new JSConfetti();
         jsConfetti.addConfetti();
    }

    if (numberToGuess < myNumber.value) {
        headline.innerHTML = "The number is smaller!";
    }

    if (numberToGuess > myNumber.value) {
        headline.innerHTML = "The number is bigger!";
    }

    myNumber.value = " ";
}