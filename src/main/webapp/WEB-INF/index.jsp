<html>
<head>
    <title>Spell Checker</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script type="application/javascript">
        var typingTimer;                //timer identifier
        var doneTypingInterval = 1000;  //time in ms, 5 second for example
        $(document).ready(function () {
            $('#result').hide();
            //on keyup, start the countdown
            $('#inputText').keyup(function () {
                clearTimeout(typingTimer);
                if ($('#inputText').val) {
                    typingTimer = setTimeout(function () {
                        //do stuff here e.g ajax call etc....
                        var v = $("#inputText").val();
                        $.ajax({
                            url: "/check/" + v, success: function (result) {
                                if (result) {
                                    $('#result').show();
                                    if (result.dictFound) {
                                        $("#dictFound").text(result.dictFound);
                                    }
                                    if (result.suggestions.length > 0) {
                                        $('#suggestions').show();
                                        let data = '<h3>Suggestions : </h3><ul>';
                                        for (let i = 0; i < result.suggestions.length; i++) {
                                            data += '<li>' + result.suggestions[i] + '</li>';
                                        }
                                        data += '</ul>';
                                        $('#suggestions').html(data);
                                    } else {
                                        $('#suggestions').hide();
                                    }
                                } else {
                                    $('#result').hide();
                                }
                            }
                        });
                    }, doneTypingInterval);
                }
            });
        });
    </script>
</head>
<body>
<h1>Welcome to Santosh Spell Checker</h1>
<label for="inputText">Enter Any Word</label><br/>
<input type="text" placeholder="Enter any word" id="inputText"/>
<br/>
<div id="result">
    <h3>Word Found in dictionary : <span id="dictFound"></span></h3>
    <div id="suggestions">

    </div>
</div>
</body>
</html>