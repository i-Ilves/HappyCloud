let notes = [];

async function getNotes() {
    let result = await fetch('/rest/notes/');
    notes = await result.json();

    renderNotes();
}

function renderNotes() {
    let notesList = document.querySelector("#notes-list");

    notesList.innerHTML = "";

    for(let note of notes) {
        let noteLi = `
            <li>
                title: ${note.title} <br>
                text: ${note.text} <br>
            </li>
        `;

        notesList.innerHTML += noteLi;
    }

}

async function getImages() {
    let result = await fetch('/rest/images/');
    images = await result.json();

    renderImages();
}

function renderImages() {
    let imagesList = document.querySelector("#images-list");

    imagesList.innerHTML = "";

    for(let image of images) {
        let imageLi = `
            <li>
                title: ${image.title} <br>
                link: ${image.link} <br>
            </li>
        `;

        imagesList.innerHTML += imageLi;
    }

}

async function getFiles() {
    let result = await fetch('/rest/files/');
    files = await result.json();

    renderFiles();
}

function renderFiles() {
    let filesList = document.querySelector("#files-list");

    filesList.innerHTML = "";

    for(let file of files) {
        let fileLi = `
            <li>
                title: ${file.title} <br>
                link: ${file.link} <br>
            </li>
        `;

        filesList.innerHTML += fileLi;
    }

}




async function createUser() {
    let user = {
        name: "Cassandra",
        age: 48
    }

    let result = await fetch("/rest/users", {
        method: "POST",
        body: JSON.stringify(user)
    });

    console.log(await result.text())
}