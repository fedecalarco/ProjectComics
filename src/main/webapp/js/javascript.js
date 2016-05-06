var sessionUser;
var webSocket;
var urlWebSocket = 'ws://localhost:8084/YourComics/WS';


$(document).ready(function () {
    webSocket = new WebSocket(urlWebSocket);

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

});

$('#btnLogin').click(function () {
    var username = $('#username').val();
    var pass = $('#pass').val();

    webSocket.send(
            JSON.stringify(
                    {
                        username: username,
                        password: pass,
                        type: "login"

                    }
            ));
});





function authentication(user) {
//    console.log(user);
    if (user.ok === "true") {
        sessionStorage.user = JSON.stringify(user);
        sessionUser = JSON.parse(sessionStorage.user);
        var welcome = "<a onclick='userProfile(" + sessionUser.id + ")'>" + sessionUser.username + "</a>";
        $('#headerRight').html('<b>Welcome ' + welcome + '</b>');
        $('#myModal').modal('hide');
    } else {
        $('#messages').html("User or password invalid");
    }

}

function userProfile(id) {
    var userModal = $('#userModal');
    $('#userBody').html('<p><b>Username: </b>' + sessionUser.username + '</p> <p><b>Firstname: </b> ' + sessionUser.firstName + '</p><p><b>Lastname: </b> ' + sessionUser.lastName + '</p>');
    userModal.modal();
}







function showAll(comics) {
    var comics = comics;
    var parent = $('#allComics');
    parent.empty();

    for (i = 0; i < comics.length; i++) {

        a = '<article class="col-md-2 col-centered" id="comic" > <figure onclick="showDetails(' + comics[i].id + ')"><img src="img/' + comics[i].cover + '" width="215" height="315" alt=""><figcaption>' + comics[i].character.name + ' - ' + comics[i].title + '</figcaption> </figure></article>';
        parent.append(a);
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

    $('#comicTitle').html('<h3><b>' + comic.title + '</b></h3>');
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



var subNav = $("#nav2");

$("#nav-genres").click(function () {

    subNav.html(
            '<div class="col-md-2"><a href="#">Superhero</a></div>' +
            '<div class="col-md-2"><a href="#">Horror</a></div>' +
            '<div class="col-md-2"><a href="#">Non-Fiction</a></div>' +
            '<div class="col-md-2"><a href="#">Science-Fiction</a></div>' +
            '<div class="col-md-2"><a href="#">Others</a></div>'
            );

    subNav.show();
});


$('#nav-editions').click(
        function () {
            subNav.html(
                    '<div class="col-md-2"><a href="#">Standard</a></div>' +
                    '<div class="col-md-2"><a href="#">Collector</a></div>'

                    );

            subNav.show();

        });

$('#nav-characters').click(
        function () {

            subNav.html(
                    '<div class="col-md-2"><a href="#">Batman</a></div>' +
                    '<div class="col-md-2"><a href="#">X-Men</a></div>' +
                    '<div class="col-md-2"><a href="#">Spiderman</a></div>' +
                    '<div class="col-md-2"><a href="#">Spawn</a></div>'

                    );

            subNav.show();

        });

        