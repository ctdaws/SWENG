var buttonList = [];

        function sendGet() {
            var value;
            var url = "http://localhost:9000/echoGet";
            $.get(url, function(data) {
               value = data;
               generateForm(value);
            });
            return value;
        }

        function sendPost(button) {
            var url = "http://localhost:9000/echoPost";
            $.post(url, button);
        }

        function formRefresh() {
          sendGet();
          Console.log("Refreshed get.");
          var t = setTimeout(formRefresh, 1000);
        }

        function generateForm(data) {
            var formData = JSON.parse(data);

            switch(formData.type) {
                case "none":
                    noneHandler(formData);
                    break;
                case "question":
                    questionHandler(formData);
                    break;
                case "feedback":
                    feedbackHandler(formData);
                    break;
            }
        }

        function clearForm() {
            while(buttonList[0]) {
              buttonList.shift();
            }
            document.getElementById("formDiv").innerHTML = "";
        }

        function newText(text) {
            var newText = document.createElement("p");
            newText.innerHTML = text;
            document.getElementById("formDiv").appendChild(newText);
        }

        function newAnswerButton(display, value, color) {
            var newButton = document.createElement("button");
            newButton.className = "w3-bar-item w3-button w3-" + color + " w3-padding-large w3-xxxlarge w3-round w3-half";
            //newButton.style.width = "300px";
            newButton.innerHTML = display;
            newButton.addEventListener("click", function(){disableInputs(); sendPost(value);});
            document.getElementById("formDiv").appendChild(newButton);
            buttonList.push(newButton);
        }

        function newFeedbackButton(backgroundImg, value) {
            var newButton = document.createElement("button");
            newButton.addEventListener("click", function(){disableInputs(); sendPost(value);});
            newButton.style.width = "100px";
            newButton.style.height = "100px";
            newButton.style.backgroundImage = backgroundImg;
            newButton.style.backgroundSize = "100px 100px";
            document.getElementById("formDiv").appendChild(newButton);
            buttonList.push(newButton);
        }

        function noneHandler(formData) {
          newText("No question ATM.");
        }

        function questionHandler(formData) {
            newText(formData.questionText);
            var colorTable = ["red", "indigo", "light-green", "amber"];
            var colorIndex = 0;
            var formOptionsLength = formData.form.length;
            for(i = 0; i < formOptionsLength; i++) {
                if (formData.form[i].type == "button") {
                    newAnswerButton(formData.form[i].display, formData.form[i].return, colorTable[colorIndex++]);
                }
            }
        }

        function feedbackHandler(formData) {
            var formOptionsLength = formData.form.length;
            for(i = 0; i <formOptionsLength; i++) {
                if(formData.form[i].type == "button") {
                    newFeedbackButton(formData.form[i].backgroundImg, formData.form[i].return);
                }
            }
        }


		function disableInputs() {
			for(i = 0; i < buttonList.length; i++) {
			    buttonList[i].disabled = true;
            }
		}
