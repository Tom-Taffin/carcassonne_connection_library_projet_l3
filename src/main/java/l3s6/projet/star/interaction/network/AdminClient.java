package l3s6.projet.star.interaction.network;

import java.net.URISyntaxException;

import l3s6.projet.star.interaction.command.*;
import l3s6.projet.star.interaction.router.GameListener;

public class AdminClient extends PlayerClient {

    public AdminClient(String ip, int port, String id, GameListener updateListener) throws URISyntaxException, InterruptedException{
        super(ip, port, id, updateListener);

    }

    public void expel(String expelledPlayer){
        try {
            ExpelCommand expelCommand = new ExpelCommand();
            this.cws.send(expelCommand.build(id, expelledPlayer));
        } catch (InvalidArgumentNumberException e) {
            e.printStackTrace();
        }
    }

    public void grant(String grantedPlayer, String... keywords){
        try {
            GrantCommand grantCommand = new GrantCommand();
            this.cws.send(grantCommand.build(id, grantedPlayer, keywords));
        } catch (InvalidArgumentNumberException e) {
            e.printStackTrace();
        }
    }

    public void offer(String player, String tile){
        try {
            OfferCommand offerCommand = new OfferCommand();
            this.cws.send(offerCommand.build(id, player, tile));
        } catch (InvalidArgumentNumberException e) {
            e.printStackTrace();
        }
    }

    public void close(){
        try {
            CloseCommand closeCommand = new CloseCommand();
            this.cws.send(closeCommand.build(id));
        } catch (InvalidArgumentNumberException e) {
            e.printStackTrace();
        }
    }

    public void elect(String role, String... ids){
        try {
            ElectCommand electCommand = new ElectCommand();
            this.cws.send(electCommand.build(id, role, ids));
        } catch (InvalidArgumentNumberException e) {
            e.printStackTrace();
        }
    }

    public void score(String other_id, int points){
        try {
            ScoreCommand scoreCommand = new ScoreCommand();
            this.cws.send(scoreCommand.build(id, other_id, points));
        } catch (InvalidArgumentNumberException e) {
            e.printStackTrace();
        }
    }

    public void start(){
        try {
            StartCommand startCommand = new StartCommand();
            this.cws.send(startCommand.build(id, null));
        } catch (InvalidArgumentNumberException e) {
            e.printStackTrace();
        }
    }

    public void end(String... ids){
        try {
            EndCommand endCommand = new EndCommand();
            this.cws.send(endCommand.build(id, ids));
        } catch (InvalidArgumentNumberException e) {
            e.printStackTrace();
        }
    }

}
