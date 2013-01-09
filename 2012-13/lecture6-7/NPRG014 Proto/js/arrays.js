var listOfMails = [ '1st mail', '2nd mail', '3rd mail', '...'];

console.log('The array content via for:');
for(var i = 0; i < listOfMails.length; i++) {
    console.log('Array[' + i + '] = ' + listOfMails[i]);
}

listOfMails[4] = '5th mail';
listOfMails[3] = '4th mail';
listOfMails.push('6th mail');
console.log("\nModified array: " + listOfMails);
console.log("...removing the last item...");
listOfMails.pop();
console.log("Joined array elements: " + listOfMails.join(' --> '));
