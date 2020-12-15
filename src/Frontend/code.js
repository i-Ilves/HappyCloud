let notes = [];
let urlids = [];


async function getNotes() {
    let result = await fetch('/rest/notes');
    notes = await result.json();
    renderNotes();
}

function renderNotes() {
    
    let notesList = document.querySelector("#notes-list");
    let noteLi;
    notesList.innerHTML = "";

    for(let note of notes) {
        if (note.image_url == "") {
            noteLi = `
            <li id="note-list-item-${note.id}">
                title: ${note.title} <br>
                text: ${note.text} <br>
                date: ${new Date(note.date).toLocaleDateString()} <br>
                <button class="edit-button" type="button" onclick="loadNote(${note.id})">Edit</button>
                <button class="delete-button" type="button" onclick="deleteNote(${note.id})">Delete</button>
            </li>
            `;
        } else {
            noteLi = `
            <li id="note-list-item-${note.id}">
                <img src="${note.image_url}">
                title: ${note.title} <br>
                text: ${note.text} <br>
                date: ${new Date(note.date).toLocaleDateString()} <br>
                <button class="edit-button" type="button" onclick="loadNote(${note.id})">Edit</button>
                <button class="delete-button" type="button" onclick="deleteNote(${note.id})">Delete</button>
            </li>
            `;
        }

        notesList.innerHTML += noteLi;
    }

}

function loadNote(id) {

    for(let note of notes) {
        if (note.id == id) {
            $("#select-url-id").val(note.url_id);
            $("#note-title-text").val(note.title);
            $("#note-text-text").val(note.text);
            $("#note-id").val(note.id);
            console.log(note.image_url);
        }
    }

    // Faking a new form for updates using the same form
    $("#note-image, label[for='note-image']").remove();
    $("#note-file, label[for='note-file']").remove();

}

async function deleteNote(id) {

    noteToDelete = {
        id: id
    };

    let result = await fetch("/rest/notes/delete", {
        method: "DELETE",
        body: JSON.stringify(noteToDelete)
    });

    console.log(await result.text());

    $("#note-list-item-" + id).remove();
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


$("#new-note-form").submit(async function (event) {
    
    event.preventDefault();

    let urlId = $("#select-url-id").val();
    let noteTitle = $("#note-title-text").val();
    let noteText = $("#note-text-text").val(); 
    let noteID = $("#note-id").val(); 
    let noteDate = Date.now();

    // Is hidden field empty - then make a new post
    if (noteID == "") {

        let noteToAdd = {};

        noteToAdd = {
            text: noteText,
            url_id: urlId,
            date: noteDate,
            title: noteTitle
        }

        if( $('#note-image').length > 0) {

            let images = document.getElementById('note-image').files;
            let formData = new FormData();

            for(let image of images) {
                formData.append('images', image, image.name);
            }

            let uploadResult = await fetch('/api/upload/image', {
                method: 'POST',
                body: formData
            });

            noteToAdd.image_url = await uploadResult.text();
            
        }
        
        if( $('#note-file').length > 0) {

            let files = document.getElementById('note-file').files;
            let formData = new FormData();

            for(let file of files) {
                formData.append('files', file, file.name);
            }

            let uploadResult = await fetch('/api/upload/file', {
                method: 'POST',
                body: formData
            });

            noteToAdd.file_url = await uploadResult.text();
            
        }

        addNoteToDB(noteToAdd);

        // Else if id is not empty update
    } else {
        
        let noteToEdit = {
            text: noteText,
            url_id: urlId,
            date: noteDate,
            title: noteTitle,
            id: noteID
        }
    
        updateNoteAtDB(noteToEdit);
    }

})

async function addNoteToDB(noteToAdd) {
    let result = await fetch("/rest/notes", {
        method: "POST",
        body: JSON.stringify(noteToAdd)
    });

    console.log(await result.text());
}

async function updateNoteAtDB(noteToEdit) {
    let result = await fetch("/rest/notes/update", {
        method: "POST",
        body: JSON.stringify(noteToEdit)
    });

    console.log(await result.text());
}