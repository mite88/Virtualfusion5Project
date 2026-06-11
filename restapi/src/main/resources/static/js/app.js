function testCall() {
    console.log('hello, world!')
}

function getPost() {

    if ( !validate() ) {
        return;
    }

    fetch(`http://localhost:8080/posts/${getSequence()}`)
        .then(resp => resp.json())
        .then(data => {

            refresh(
                data.data.title,
                data.data.contents,
                data.data.author
            );

            alert('가장 최신 글로 데이터를 갱신했습니다!');

        })
        .catch(err => console.log(err));

}


function createPost() {

    const data  = getPostValues();

    fetch(`http://localhost:8080/posts`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
    }).then(
        resp => resp.json()
    ).then(
        data => {
            updateSequence(data.data.id)
            alert(data.message);
            clearPostBody();
        }
    )
        .catch(
            err => {
                console.log(err);
            }
        )

}