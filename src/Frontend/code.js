let notes = [];
let urlids = [];


async function getNotes() {
    let result = await fetch('/rest/notes');
    notes = await result.json();
    console.log(notes);
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
                date: ${new Date(note.date).toLocaleDateString()}
            </li>
        `;

        notesList.innerHTML += noteLi;
    }

}

async function getImages() {
    let result = await fetch('/rest/images');
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
    let result = await fetch('/rest/files');
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

async function getURLids() {
    let result = await fetch('/rest/url-ids');
    urlids = await result.json();

    renderURLids();
}

function renderURLids() {
    let selectUrlIdDropdown = document.querySelector("#select-url-id");

    selectUrlIdDropdown.innerHTML = "";

    for(let urlId of urlids) {
        let urlIdOption = `
        <option value="${urlId.id}">${urlId.link}</option>
        `;

        selectUrlIdDropdown.innerHTML += urlIdOption;
    }

}

getURLids();


$("#new-note-form").submit(function (event) {
    
    event.preventDefault();

    let urlId = $("#select-url-id").val();
    let noteTitle = $("#note-title-text").val();
    let noteText = $("#note-text-text").val(); 
    let noteDate = Date.now();

    let noteToAdd = {
        text: noteText,
        url_id: urlId,
        date: noteDate,
        title: noteTitle
    }
    
    addNoteToDB(noteToAdd);

})

async function addNoteToDB(noteToAdd) {
    console.log(noteToAdd);
    let result = await fetch("/rest/notes", {
        method: "POST",
        body: JSON.stringify(noteToAdd)
    });

    console.log(await result.text());
}