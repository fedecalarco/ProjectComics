var sessionUser;

var webSocket = new WebSocket('ws://localhost:8080/YourComics/WS');

webSocket.onmessage = function (event) {
    var eventJson = JSON.parse(event.data);
    switch (eventJson.type) {
        case "login":
            authentication(eventJson);
            break;
        case "allComics":
            showAll(eventJson.allComics);
            break;
        case "descriptionComic":
            describeComic(eventJson.comic);
            break;
        default:
            alert("Error: Type not found");
    }
};

webSocket.onerror = function (evt) {
    alert("WS: Connection failed. Check Logs");
};



// USER LOGIN 

$('#btnLogin').click(function () {
    webSocket.send(
            JSON.stringify(
                    {
                        username: $('#username').val(),
                        password: $('#pass').val(),
                        type: "login"

                    }
            ));
});

function authentication(user) {
    if (user.ok === "true") {
        sessionStorage.user = JSON.stringify(user);
        sessionUser = JSON.parse(sessionStorage.user);
        var welcome = "<a onclick='userProfile(" + sessionUser.id + ")'>" + sessionUser.username + "</a>";
        $('#headerRight').html('<b>Welcome ' + welcome + '</b>');
        $('#loginModal').modal('hide');
    } else {
        $('#messages').html("Your username or password was incorrect. Please try again.");
    }

}

function userProfile(id) {
    $('#userBody').html('<p><b>Username: </b>' + sessionUser.username + '</p> <p><b>Firstname: </b> ' + sessionUser.firstName + '</p><p><b>Lastname: </b> ' + sessionUser.lastName + '</p>');
    $('#userModal').modal("show");
}







function showAll(comics) {


    var comics = comics;
    var parent = $('#lstComics');
    parent.empty();

    if (comics.length === 0) {
        parent.html('<p>0 comics found</p>');
        $('footer').addClass('navbar navbar-fixed-bottom');
    } else {
        $('footer').removeClass('navbar navbar-fixed-bottom');
        for (i = 0; i < comics.length; i++) {

            a = '<article class="col-md-3 col-centered animated fadeIn" id="comic"  > <figure onclick="showDetails(' + comics[i].id + ')"><img src="img/' + comics[i].cover + '" width="215" height="315" alt=""><figcaption>' + comics[i].character.name + ' - ' + comics[i].title + '</figcaption> </figure></article>';
            parent.append(a);
        }
    }
}



function showDetails(id) {
    webSocket.send(
            JSON.stringify(
                    {
                        type: "getComic",
                        id: id

                    }
            ));
}
function describeComic(comic) {

    $('#comicTitle').html(comic.title);
    $('#comicBody').html(
            '<article> <img src="img/' + comic.cover + '" width="215" height="315"></img> <br> <p><b>Character: </b>' + comic.character.name + '</p>  <p><b>Number: </b>' + comic.number + '</p> <p><b>Genre: </b>' + comic.genre.name + '</p> <p><b>Publisher: </b>' + comic.publisher.name + '</p> <p><b>Rating: </b>' + comic.rating + '</p> <p><b>Available: </b>' + comic.available + '</p> </article>'

            );
    $('#comicModal').modal();
}



function getAllComic() {
    webSocket.send(
            JSON.stringify(
                    {
                        type: "getAllComics"

                    }
            ));
}

// BUTTONS NAVBAR

var subNav = $("#nav2");

$("#nav-genres").hover(function () {
    subNav.empty();
    var genres = ["Superhero", "Horror", "Non-Fiction", "Science-Fuction", "Others"];
    for (i = 0; i < genres.length; i++) {
        a = '<div class="col-md-2"> <a href="#" onClick="getComicsFilter(\'' + genres[i] + '\')">' + genres[i] + '</a></div>';
        subNav.append(a);
    }
    subNav.show();
});

$('#nav-editions').hover(
        function () {

            subNav.empty();
            var editions = ["Standard", "Collector"];
            for (i = 0; i < editions.length; i++) {
                a = '<div class="col-md-2"> <a href="#" onClick="getComicsFilter(\'' + editions[i] + '\')">' + editions[i] + '</a></div>';
                subNav.append(a);
            }
            subNav.show();
        });


$('#nav-characters').hover(
        function () {
            subNav.empty();
            var characters = ["Batman", "X-Men", "Spiderman", "Spawn"];
            for (i = 0; i < characters.length; i++) {
                a = '<div class="col-md-2"> <a href="#" onClick="getComicsFilter(\'' + characters[i] + '\')">' + characters[i] + '</a></div>';
                subNav.append(a);
            }
            subNav.show();
        });


function getComicsFilter(filter) {

    webSocket.send(
            JSON.stringify(
                    {
                        type: "getComicsFilter",
                        filter: filter

                    }
            ));
}
;
