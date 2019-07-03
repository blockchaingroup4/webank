var cards = document.querySelectorAll('.card');
for(var i = 0; i < cards.length; i++){
    cards[i].addEventListener( 'click', function() {
        this.classList.toggle('is-flipped');
    });
}


// $('.card').each(function (card) {
//     card.on( 'click', function() {
//         card.classList.toggle('is-flipped');
//     });
// })